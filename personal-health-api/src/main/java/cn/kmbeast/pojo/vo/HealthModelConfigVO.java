package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.HealthModelConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Health Model VO Class
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HealthModelConfigVO extends HealthModelConfig {
    /**
     * user name
     */
    private String userName;
}
