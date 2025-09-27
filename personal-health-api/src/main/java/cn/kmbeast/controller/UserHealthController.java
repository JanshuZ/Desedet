package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserHealthQueryDto;
import cn.kmbeast.pojo.entity.UserHealth;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.UserHealthVO;
import cn.kmbeast.service.UserHealthService;
import cn.kmbeast.utils.DateUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for user health records
 */
@RestController
@RequestMapping(value = "/user-health")
public class UserHealthController {

    @Resource
    private UserHealthService userHealthService;


    /**
     * User Health Record Addition
     *
     * @param userHealths Add new data
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody List<UserHealth> userHealths) {
        return userHealthService.save(userHealths);
    }

    /**
     * Delete user health records
     *
     * @param ids List of user health record IDs to be deleted
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return userHealthService.batchDelete(ids);
    }

    /**
     * User Health Record Modification
     *
     * @param userHealth parameter
     * @return Result<Void> response
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody UserHealth userHealth) {
        return userHealthService.update(userHealth);
    }

    /**
     * User Health Record Query
     *
     * @param userHealthQueryDto Universal Response Body
     * @return Result<List < UserHealthVO>> Universal Response Body
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<UserHealthVO>> query(@RequestBody UserHealthQueryDto userHealthQueryDto) {
        return userHealthService.query(userHealthQueryDto);
    }

    /**
     * User Health Record Query
     *
     * @param id  Health Model ID
     * @param day How many days ahead
     * @return Result<List < UserHealthVO>> Universal Response Body
     */
    @GetMapping(value = "/timeQuery/{id}/{day}")
    public Result<List<UserHealthVO>> timeQuery(@PathVariable Integer id,
                                                @PathVariable Integer day) {
        //With a time frame and a health model ID, what else do we need?
        Integer userId = LocalThreadHolder.getUserId();
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        UserHealthQueryDto userHealthQueryDto = new UserHealthQueryDto();
        userHealthQueryDto.setHealthModelConfigId(id);
        userHealthQueryDto.setUserId(userId);
        userHealthQueryDto.setStartTime(queryDto.getStartTime());
        userHealthQueryDto.setEndTime(queryDto.getEndTime());
        return userHealthService.query(userHealthQueryDto);
    }

    /**
     * Statistical model stock data
     *
     * @return Result<List < ChartVO>> Response results
     */
    @GetMapping(value = "/daysQuery/{day}")
    @ResponseBody
    public Result<List<ChartVO>> query(@PathVariable Integer day) {
        return userHealthService.daysQuery(day);
    }

}
