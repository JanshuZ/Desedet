package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.aop.Protector;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.EvaluationsQueryDto;
import cn.kmbeast.pojo.entity.Evaluations;
import cn.kmbeast.service.EvaluationsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Comment on Controller
 */
@RestController
@RequestMapping("/evaluations")
public class EvaluationsController {

    @Resource
    private EvaluationsService evaluationsService;

    /**
     * comment
     *
     * @return Result<String>
     */
    @Protector
    @PostMapping(value = "/insert")
    @ResponseBody
    public Result<Object> insert(@RequestBody Evaluations evaluations) {
        return evaluationsService.insert(evaluations);
    }

    /**
     * Comment modification
     *
     * @return Result<String>
     */
    @Protector
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Evaluations evaluations) {
        return evaluationsService.update(evaluations);
    }

    /**
     * Search for all comments under the content
     *
     * @return Result<String>
     */
    @Protector
    @GetMapping(value = "/list/{contentId}/{contentType}")
    @ResponseBody
    public Result<Object> list(@PathVariable Integer contentId,
                               @PathVariable String contentType) {
        return evaluationsService.list(contentId, contentType);
    }

    /**
     * Page search for comments
     *
     * @return Result<String>
     */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<Object> query(@RequestBody EvaluationsQueryDto evaluationsQueryDto) {
        return evaluationsService.query(evaluationsQueryDto);
    }

    /**
     * Batch delete comment data
     *
     * @return Result<String>
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Object> batchDelete(@RequestBody List<Integer> ids) {
        return evaluationsService.batchDelete(ids);
    }

    /**
     * Delete comment information through ID
     *
     * @return Result<String>
     */
    @Protector
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        return evaluationsService.delete(id);
    }

}

