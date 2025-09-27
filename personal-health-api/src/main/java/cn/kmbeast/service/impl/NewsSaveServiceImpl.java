package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.NewsSaveMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsSaveQueryDto;
import cn.kmbeast.pojo.entity.NewsSave;
import cn.kmbeast.pojo.vo.NewsSaveVO;
import cn.kmbeast.service.NewsSaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Health Information Collection Business Logic
 */
@Service
public class NewsSaveServiceImpl implements NewsSaveService {

    @Resource
    private NewsSaveMapper newsSaveMapper;

    /**
     * New Health Information Collection
     *
     * @param newsSave parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(NewsSave newsSave) {
        newsSave.setCreateTime(LocalDateTime.now());

        newsSaveMapper.save(newsSave);
        return ApiResult.success();
    }

    /**
     * Delete Health Information Collection
     *
     * @param ids parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        newsSaveMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * Health Information Collection Query
     *
     * @param newsSaveQueryDto Universal Response Body
     * @return Result<List < NewsSaveVO>>
     */
    @Override
    public Result<List<NewsSaveVO>> query(NewsSaveQueryDto newsSaveQueryDto) {
        List<NewsSaveVO> tagsList = newsSaveMapper.query(newsSaveQueryDto);
        Integer totalCount = newsSaveMapper.queryCount(newsSaveQueryDto);
        return PageResult.success(tagsList, totalCount);
    }

    /**
     * Information collection or cancellation of collection
     *
     * @param newsSave Information Collection Entity
     * @return Result<Void>
     */
    @Override
    public Result<Void> operation(NewsSave newsSave) {
        //Collection - Add; Cancel Collection - Delete
        newsSave.setUserId(LocalThreadHolder.getUserId());
        NewsSaveQueryDto newsSaveQueryDto = new NewsSaveQueryDto();
        newsSaveQueryDto.setUserId(LocalThreadHolder.getUserId());
        newsSaveQueryDto.setNewsId(newsSave.getNewsId());
        List<NewsSaveVO> newsSaveVOS = newsSaveMapper.query(newsSaveQueryDto);
        if (newsSaveVOS.isEmpty()) {
            newsSave.setCreateTime(LocalDateTime.now());
            newsSaveMapper.save(newsSave);
        } else {
            List<Long> ids = Collections.singletonList(Long.valueOf(newsSaveVOS.get(0).getId()));
            newsSaveMapper.batchDelete(ids);
        }
        return ApiResult.success();
    }
}
