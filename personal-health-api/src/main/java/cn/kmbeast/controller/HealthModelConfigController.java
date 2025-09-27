package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.aop.Protector;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HealthModelConfigQueryDto;
import cn.kmbeast.pojo.entity.HealthModelConfig;
import cn.kmbeast.pojo.vo.HealthModelConfigVO;
import cn.kmbeast.service.HealthModelConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller of Health Model
 */
@RestController
@RequestMapping(value = "/health-model-config")
public class HealthModelConfigController {

    @Resource
    private HealthModelConfigService healthModelConfigService;

    /**
     * New Health Model
     *
     * @param healthModelConfig Add new data
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody HealthModelConfig healthModelConfig) {
        //This is for users to add new models, which are not global models
        healthModelConfig.setIsGlobal(false);
        return healthModelConfigService.save(healthModelConfig);
    }

    /**
     * New Health Model (Administrator)
     *
     * @param healthModelConfig Add new data
     * @return Result<Void> Universal Response Body
     */
    @Protector(role = "管理员") //Add this annotation, only administrators have the authority to operate this interface
    @PostMapping(value = "config/save")
    public Result<Void> configSave(@RequestBody HealthModelConfig healthModelConfig) {
        //Set as global configuration
        healthModelConfig.setIsGlobal(true);
        return healthModelConfigService.save(healthModelConfig);
    }

    /**
     * Delete Health Model
     *
     * @param ids List of health model IDs to be deleted
     * @return Result<Void> Universal Response Body
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return healthModelConfigService.batchDelete(ids);
    }

    /**
     * Modification of Health Model
     *
     * @param healthModelConfig parameter
     * @return Result<Void> response
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody HealthModelConfig healthModelConfig) {
        return healthModelConfigService.update(healthModelConfig);
    }

    /**
     * Query the user's own configured model and global model
     *
     * @return Result<List < HealthModelConfigVO>> Universal response
     */
    @Pager
    @PostMapping(value = "/modelList")
    public Result<List<HealthModelConfigVO>> modelList() {
        return healthModelConfigService.modelList();
    }

    /**
     * Health Model Query
     *
     * @param healthModelConfigQueryDto Query parameters
         * @return Result<List < HealthModelConfigVO>> Universal response
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<HealthModelConfigVO>> query(@RequestBody HealthModelConfigQueryDto healthModelConfigQueryDto) {
        return healthModelConfigService.query(healthModelConfigQueryDto);
    }


}
