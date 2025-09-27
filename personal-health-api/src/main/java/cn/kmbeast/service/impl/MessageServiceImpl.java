package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.MessageMapper;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.MessageQueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.em.IsReadEnum;
import cn.kmbeast.pojo.em.MessageType;
import cn.kmbeast.pojo.em.RoleEnum;
import cn.kmbeast.pojo.entity.Message;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.pojo.vo.MessageVO;
import cn.kmbeast.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Message Service Implementation
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * Save Messages
     *
     * @param messages List of messages to save
     * @return Result<Void> Response result
     */
    @Override
    public Result<Void> save(List<Message> messages) {
        messageMapper.batchSave(messages);
        return ApiResult.success();
    }

    /**
     * When a comment is replied to by someone else, send a notification message to the replier
     *
     * @param message Message data
     * @return Result<Void>
     */
    @Override
    public Result<Void> evaluationsReplySave(Message message) {
        message.setMessageType(MessageType.EVALUATIONS_BY_REPLY.getType());
        message.setContent("Your comment has been replied to.");
        message.setIsRead(IsReadEnum.READ_NO.getStatus());
        message.setCreateTime(LocalDateTime.now());
        List<Message> entityListSave = new ArrayList<>();
        entityListSave.add(message);
        return save(entityListSave);
    }

    /**
     * When a comment is upvoted, send a notification to the comment owner
     *
     * @param message Message entity
     * @return Result<Void>
     */
    @Override
    public Result<Void> evaluationsUpvoteSave(Message message) {
        message.setMessageType(MessageType.EVALUATIONS_BY_UPVOTE.getType());
        message.setContent("Your comment has been upvoted.");
        message.setIsRead(IsReadEnum.READ_NO.getStatus());
        message.setCreateTime(LocalDateTime.now());
        List<Message> entityListSave = new ArrayList<>();
        entityListSave.add(message);
        return save(entityListSave);
    }

    /**
     * System Notifications (One type is sending notifications to all site users, another type is sending notifications to specified users)
     *
     * @param messages List of messages to save
     * @return Result<Void>
     */
    @Override
    public Result<Void> systemInfoSave(List<Message> messages) {
        return save(messages);
    }

    /**
     * Data Reminders (Since users can record multiple metrics at once, we also pass a collection here)
     *
     * @param messages List of messages to save
     * @return Result<Void>
     */
    @Override
    public Result<Void> dataWordSave(List<Message> messages) {
        return save(messages);
    }

    /**
     * Delete Messages
     *
     * @param ids List of message IDs to delete
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        messageMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * Query Messages
     *
     * @param messageQueryDto Query parameters
     * @return Result<List<MessageVO>>
     */
    @Override
    public Result<List<MessageVO>> query(MessageQueryDto messageQueryDto) {
        List<MessageVO> tagsList = messageMapper.query(messageQueryDto);
        Integer totalCount = messageMapper.queryCount(messageQueryDto);
        return PageResult.success(tagsList, totalCount);
    }

    /**
     * System Notifications for All Site Users
     *
     * @param message System notification message
     * @return Result<Void> General response body
     */
    @Override
    public Result<Void> systemInfoUsersSave(Message message) {
        // This data is the message body to be pushed to all site users
        String messageContent = message.getContent();
        // 1. First, get the IDs of all site users
        UserQueryDto userQueryDto = new UserQueryDto();
        List<User> userList = userMapper.query(userQueryDto);
        List<Message> messageList = new ArrayList<>();
        userList.forEach(user -> {
            // Do we need to push to administrators? No
            if (Objects.equals(RoleEnum.USER.getRole(), user.getUserRole())) {
                // 2. Assemble the message
                // They are regular users, not administrators, so need to push
                Message saveEntity = new Message();
                // Receiver user ID
                saveEntity.setReceiverId(user.getId());
                // Message content
                saveEntity.setContent(messageContent);
                // Mark as system notification
                saveEntity.setMessageType(MessageType.SYSTEM_INFO.getType());
                // Initial status: unread
                saveEntity.setIsRead(IsReadEnum.READ_NO.getStatus());
                // Send time
                saveEntity.setCreateTime(LocalDateTime.now());
                messageList.add(saveEntity);
            }
        });
        // 3. Batch push (save) messages
        return save(messageList);
    }

    /**
     * Mark All Messages as Read
     *
     * @return Result<Void> Response
     */
    @Override
    public Result<Void> clearMessage() {
        // This is the ID of the currently operating user
        Integer userId = LocalThreadHolder.getUserId();
        messageMapper.update(userId, IsReadEnum.READ_OK.getStatus());
        return ApiResult.success();
    }
}
