package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HealthModelConfigQueryDto extends QueryDto {

    /**
     * Model ID
     */
    private Integer id;

    /**
     * user id
     */
    private Integer userId;

    /**
     * global variable
     */
    private Boolean isGlobal;

    /**
     * configure name
     */
    private String name;
    /**
     * Configuration Introduction
     */
    private String detail;
    /**
     * Model configuration unit
     */
    private String unit;
    /**
     * Model configuration symbol
     */
    private String symbol;

}
