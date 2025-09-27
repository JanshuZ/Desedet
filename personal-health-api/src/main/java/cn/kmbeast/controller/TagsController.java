package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.TagsQueryDto;
import cn.kmbeast.pojo.entity.Tags;
import cn.kmbeast.service.TagsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for tags
 */
@RestController
@RequestMapping(value = "/tags")
public class TagsController {

    @Resource
    private TagsService tagsService;

    /**
     * Add tags
     *
     * @param tags Add new data
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Tags tags) {
        return tagsService.save(tags);
    }

    /**
     * Tag deletion
     *
     * @param ids List of tag IDs to be deleted
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return tagsService.batchDelete(ids);
    }

    /**
     * Label modification
     *
     * @param tags parameter
     * @return Result<Void> response
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Tags tags) {
        return tagsService.update(tags);
    }

    /**
     * 标签查询
     *
     * @param tagsQueryDto Universal Response Body
     * @return Result<List < Tags>> Universal Response Body
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<Tags>> query(@RequestBody TagsQueryDto tagsQueryDto) {
        return tagsService.query(tagsQueryDto);
    }



    /**
     * 获取所有标签
     *
     * @return Result<List<Tags>> 所有标签
     */
    @GetMapping(value = "/all")
    public Result<List<Tags>> getAllTags() {
        List<Tags> tags = tagsService.getAllTags();
        return ApiResult.success(tags);
    }

}
