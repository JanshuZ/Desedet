package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HealthModelConfigMapper;
import cn.kmbeast.mapper.UserHealthMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.dto.query.extend.HealthModelConfigQueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserHealthQueryDto;
import cn.kmbeast.pojo.em.IsReadEnum;
import cn.kmbeast.pojo.em.MessageType;
import cn.kmbeast.pojo.entity.HealthModelConfig;
import cn.kmbeast.pojo.entity.Message;
import cn.kmbeast.pojo.entity.UserHealth;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HealthModelConfigVO;
import cn.kmbeast.pojo.vo.UserHealthVO;
import cn.kmbeast.service.MessageService;
import cn.kmbeast.service.UserHealthService;
import cn.kmbeast.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of User Health Record Business Logic
 */
@Service
public class UserHealthServiceImpl implements UserHealthService {

    @Resource
    private UserHealthMapper userHealthMapper;
    @Resource
    private HealthModelConfigMapper healthModelConfigMapper;
    @Resource
    private MessageService messageService;

    /**
     * User Health Record Addition
     *
     * @param userHealths parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(List<UserHealth> userHealths) {
        dealMessage(userHealths);
        dealRole(userHealths);
        userHealthMapper.batchSave(userHealths);
        return ApiResult.success();
    }

    public void dealRole(List<UserHealth> userHealths) {
        LocalDateTime nowTime = LocalDateTime.now();
        Integer userId = LocalThreadHolder.getUserId();
        userHealths.forEach(userHealth -> {
            userHealth.setUserId(userId);
            userHealth.setCreateTime(nowTime);
        });
    }

    /**
     * If there are abnormal indicators, this method will forward notifications
     *
     * @param userHealths User Health Record Collection
     */
    private void dealMessage(List<UserHealth> userHealths) {
        List<Message> messageList = new ArrayList<>();
        userHealths.forEach(userHealth -> {
            userHealth.setCreateTime(LocalDateTime.now());
            Integer healthModelConfigId = userHealth.getHealthModelConfigId();
            HealthModelConfigQueryDto queryDto = new HealthModelConfigQueryDto();
            queryDto.setId(healthModelConfigId);
            List<HealthModelConfigVO> healthModelConfigs = healthModelConfigMapper.query(queryDto);
            if (!CollectionUtils.isEmpty(healthModelConfigs)) {
                HealthModelConfig healthModelConfig = healthModelConfigs.get(0);
                //The value range is 101230
                String valueRange = healthModelConfig.getValueRange();
                String[] values = valueRange.split(",");
                int mixValue = Integer.parseInt(values[0]);
                int maxValue = Integer.parseInt(values[1]);
                double value = Double.parseDouble(String.valueOf(userHealth.getValue()));
                if (value < mixValue || value > maxValue) {
                    Message message = sendMessage(healthModelConfig, userHealth);
                    messageList.add(message);
                }
            }
        });
        if (!CollectionUtils.isEmpty(messageList)) {
            messageService.dataWordSave(messageList);
        }
    }

    /**
     * Processing user health records that comply with message notifications
     *
     * @param userHealth 用户健康记录
     * @return List<Message>
     */
    private Message sendMessage(HealthModelConfig healthModelConfig, UserHealth userHealth) {
        Message message = new Message();
        //Indicator reminder notifications
        message.setMessageType(MessageType.DATA_MESSAGE.getType());
        //Message reminder time
        message.setCreateTime(LocalDateTime.now());
        //Has it been read
        message.setIsRead(IsReadEnum.READ_NO.getStatus());
        //Receiver
        message.setReceiverId(LocalThreadHolder.getUserId());
        //Message Body
        message.setContent("The [" + healthModelConfig.getName() + "] you recorded exceeds the normal range: [" + healthModelConfig.getValueRange() + "]. Please take a rest and seek medical attention if necessary!");
        return message;
    }

    /**
     * Delete user health records
     *
     * @param ids parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        userHealthMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     *User Health Record Modification
     *
     * @param userHealth parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(UserHealth userHealth) {
        userHealthMapper.update(userHealth);
        return ApiResult.success();
    }

    /**
     * User Health Record Query
     *
     * @param userHealthQueryDto Universal Response Body
     * @return Result<List < UserHealthVO>>
     */
    @Override
    public Result<List<UserHealthVO>> query(UserHealthQueryDto userHealthQueryDto) {
        List<UserHealthVO> userHealthVOS = userHealthMapper.query(userHealthQueryDto);
        Integer totalCount = userHealthMapper.queryCount(userHealthQueryDto);
        return PageResult.success(userHealthVOS, totalCount);
    }

    /**
     * Statistical model stock data
     *
     * @return Result<List < ChartVO>> Response results
     */
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        UserHealthQueryDto userHealthQueryDto = new UserHealthQueryDto();
        userHealthQueryDto.setStartTime(queryDto.getStartTime());
        userHealthQueryDto.setEndTime(queryDto.getEndTime());
        List<UserHealthVO> userHealthVOS = userHealthMapper.query(userHealthQueryDto);
        List<LocalDateTime> localDateTimes = userHealthVOS.stream().map(UserHealthVO::getCreateTime).collect(Collectors.toList());
        List<ChartVO> chartVOS = DateUtil.countDatesWithinRange(day, localDateTimes);
        return ApiResult.success(chartVOS);
    }


}
