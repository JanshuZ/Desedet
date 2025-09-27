package cn.kmbeast.controller;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.service.ViewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *Visual interface
 */
@RestController
@RequestMapping(value = "/views")
public class ViewsController {

    @Resource
    private ViewsService viewsService;

    /**
     * Collect basic data of some systems
     *
     * @return Result<List < ChartVO>>
     */
    @GetMapping("/staticControls")
    public Result<List<ChartVO>> staticControls() {
        return viewsService.staticControls();
    }

}
