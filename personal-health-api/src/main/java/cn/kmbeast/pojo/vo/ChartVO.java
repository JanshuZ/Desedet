package cn.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data source VO
 */
@Data
@AllArgsConstructor
public class ChartVO {
    /**
     * Description item: It can be time or specific statistical item
     */
    private String name;
    /**
     * Total number of data
     */
    private Integer count;
}
