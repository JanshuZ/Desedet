package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsSaveQueryDto;
import cn.kmbeast.pojo.entity.NewsSave;
import cn.kmbeast.pojo.vo.NewsSaveVO;
import cn.kmbeast.service.NewsSaveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *Controller for Information Collection
 */
@RestController
@RequestMapping(value = "/news-save")
public class NewsSaveController {

    @Resource
    private NewsSaveService newsSaveService;

    /**
     * Information collection or cancellation of collection
     *
     * @param newsSave Add new data
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/operation")
    public Result<Void> operation(@RequestBody NewsSave newsSave) {
        return newsSaveService.operation(newsSave);
    }

    /**
     *Favorite or Cancel Favorite Operation
     *
     * @param newsSave Add new data
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody NewsSave newsSave) {
        return newsSaveService.save(newsSave);
    }


    /**
     * Information collection deletion
     *
     * @param ids List of information collection IDs to be deleted
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return newsSaveService.batchDelete(ids);
    }

    /**
     * Search for user's favorite health information
     *
     * @param newsSaveQueryDto Universal Response Body
     * @return Result<List < NewsSaveVO>> Universal Response Body
     */
    @Pager
    @PostMapping(value = "/queryUser")
    public Result<List<NewsSaveVO>> queryUser(@RequestBody NewsSaveQueryDto newsSaveQueryDto) {
        newsSaveQueryDto.setUserId(LocalThreadHolder.getUserId());
        return newsSaveService.query(newsSaveQueryDto);
    }

    /**
     * 资讯收藏查询
     *
     * @param newsSaveQueryDto Universal Response Body
     * @return Result<List < NewsSaveVO>> Universal Response Body
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<NewsSaveVO>> query(@RequestBody NewsSaveQueryDto newsSaveQueryDto) {
        return newsSaveService.query(newsSaveQueryDto);
    }

}
