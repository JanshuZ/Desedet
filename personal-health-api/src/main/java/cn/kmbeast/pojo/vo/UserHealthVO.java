package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.UserHealth;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserHealthVO extends UserHealth {

    /**
     * user name

     */
    private String userName;
    /**
     * Name of Health Model
     */
    private String name;
    /**
     * Health Model Unit
     */
    private String unit;
    /**
     * Health Model Symbols
     */
    private String symbol;
    /**
     * Normal value range for model configuration
     */
    private String valueRange;

}
