package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.MessageQueryDto;
import cn.kmbeast.pojo.em.IsReadEnum;
import cn.kmbeast.pojo.em.MessageType;
import cn.kmbeast.pojo.entity.Message;
import cn.kmbeast.pojo.vo.MessageTypeVO;
import cn.kmbeast.pojo.vo.MessageVO;
import cn.kmbeast.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Message Controller
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * Query all message types
     *
     * @return Result<List < MessageTypeVO>> Universal Response Body
     * //System notification: One type is required to be received by all users on the entire site; One is to send a specified message to a designated user.
     */
    @GetMapping(value = "/types")
    public Result<List<MessageTypeVO>> all() {
        MessageType[] messageTypes = MessageType.values();
        List<MessageTypeVO> messageTypeVOS = new ArrayList<>();
        for (MessageType messageType : messageTypes) {
            MessageTypeVO messageTypeVO = new MessageTypeVO(messageType.getType(), messageType.getDetail());
            messageTypeVOS.add(messageTypeVO);
        }
        return ApiResult.success(messageTypeVOS);
    }

    /**
     * System notifications for the entire site
     *
     * @param message Message notification collection
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/systemInfoUsersSave")
    public Result<Void> systemInfoUsersSave(@RequestBody Message message) {
        return messageService.systemInfoUsersSave(message);
    }

    /**
     * message notification
     *
     * @param messages Message notification collection
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/systemInfoSave")
    public Result<Void> systemInfoSave(@RequestBody List<Message> messages) {
        messages.forEach(message -> {
            message.setMessageType(MessageType.SYSTEM_INFO.getType());
            message.setIsRead(IsReadEnum.READ_NO.getStatus());
            message.setCreateTime(LocalDateTime.now());
        });
        return messageService.systemInfoSave(messages);
    }


    /**
     * Message deletion
     *
     * @param ids List of message IDs to be deleted
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return messageService.batchDelete(ids);
    }


    /**
     * Set all messages as read
     *
     * @return Result<Void> response
     */
    @PutMapping(value = "/clearMessage")
    public Result<Void> clearMessage() {
        return messageService.clearMessage();
    }

    /**
     * Message inquiry
     *
     * @param messageQueryDto Query parameters
     * @return Result<List < MessageVO>> Universal response
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<MessageVO>> query(@RequestBody MessageQueryDto messageQueryDto) {
        return messageService.query(messageQueryDto);
    }

}
