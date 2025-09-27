package cn.kmbeast.pojo.entity;

import lombok.Data;

/**
 * Health Model Entity Class
 */
@Data
public class HealthModelConfig {
    /**
     * Primary key


     */
    private Integer id;
    /**
     * user id

     */
    private Integer userId;
    /**
     * configure name
     */
    private String name;
    /**
     * Configuration Introduction
     */
    private String detail;
    /**
     * icon
     */
    private String cover;
    /**
     * unit
     */
    private String unit;
    /**
     * symbol
     */
    private String symbol;
    /**
     * Normal range 10,20 (10,20)
     */
    private String valueRange;
    /**
     * Is it a global model
     */
    private Boolean isGlobal;
}
