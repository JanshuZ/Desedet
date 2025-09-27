package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.TagsMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.TagsQueryDto;
import cn.kmbeast.pojo.entity.Tags;
import cn.kmbeast.service.TagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Implementation of tag business logic
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Resource
    private TagsMapper tagsMapper;

    /**
     * Add tags
     *
     * @param tags parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Tags tags) {
        tagsMapper.save(tags);
        return ApiResult.success();
    }

    /**
     * Tag deletion
     *
     * @param ids parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        tagsMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * Label modification
     *
     * @param tags parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Tags tags) {
        tagsMapper.update(tags);
        return ApiResult.success();
    }

    /**
     * Tag query
     *
     * @param tagsQueryDto Universal Response Body
     * @return Result<List < Tags>>
     */
    @Override
    public Result<List<Tags>> query(TagsQueryDto tagsQueryDto) {
        List<Tags> tagsList = tagsMapper.query(tagsQueryDto);
        Integer totalCount = tagsMapper.queryCount(tagsQueryDto);
        return PageResult.success(tagsList, totalCount);
    }

    @Override
    public List<Tags> getAllTags() {
        return tagsMapper.getAllTags();
    }

}
