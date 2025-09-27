package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HealthModelConfigMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HealthModelConfigQueryDto;
import cn.kmbeast.pojo.entity.HealthModelConfig;
import cn.kmbeast.pojo.vo.HealthModelConfigVO;
import cn.kmbeast.service.HealthModelConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Health Model Business Logic
 */
@Service
public class HealthModelConfigServiceImpl implements HealthModelConfigService {

    @Resource
    private HealthModelConfigMapper healthModelConfigMapper;

    /**
     * New Health Model
     *
     * @param healthModelConfig parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(HealthModelConfig healthModelConfig) {
        //Set the user's ID
        healthModelConfig.setUserId(LocalThreadHolder.getUserId());
        healthModelConfigMapper.save(healthModelConfig);
        return ApiResult.success();
    }

    /**
     * Delete Health Model
     *
     * @param ids parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        healthModelConfigMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * Modification of Health Model
     *
     * @param healthModelConfig parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(HealthModelConfig healthModelConfig) {
        healthModelConfigMapper.update(healthModelConfig);
        return ApiResult.success();
    }

    /**
     * Query the user's own configured model and global model
     *
     *@return Result<List < HealthModelConfigVO>>
     */
    @Override
    public Result<List<HealthModelConfigVO>> modelList() {
        HealthModelConfigQueryDto healthModelConfigQueryDto = new HealthModelConfigQueryDto();
        healthModelConfigQueryDto.setUserId(LocalThreadHolder.getUserId());
        List<HealthModelConfigVO> modelConfigs = healthModelConfigMapper.query(healthModelConfigQueryDto);
        healthModelConfigQueryDto.setUserId(null);
        healthModelConfigQueryDto.setIsGlobal(true);
        List<HealthModelConfigVO> modelConfigsGlobal = healthModelConfigMapper.query(healthModelConfigQueryDto);
        List<HealthModelConfigVO> modelAll = new ArrayList<>();
        modelAll.addAll(modelConfigs);
        modelAll.addAll(modelConfigsGlobal);
        return ApiResult.success(modelAll);
    }

    /**
     * Health Model Query
     *
     * @param healthModelConfigQueryDto Universal Response Body
     * @return Result<List < HealthModelConfigVO>>
     */
    @Override
    public Result<List<HealthModelConfigVO>> query(HealthModelConfigQueryDto healthModelConfigQueryDto) {
        List<HealthModelConfigVO> modelConfigs = healthModelConfigMapper.query(healthModelConfigQueryDto);
        Integer totalCount = healthModelConfigMapper.queryCount(healthModelConfigQueryDto);
        return PageResult.success(modelConfigs, totalCount);
    }

}
