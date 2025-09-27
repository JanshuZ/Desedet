package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 禁言状态枚举
 */
@Getter
@AllArgsConstructor
public enum WordStatusEnum {

    USE(false, "available"),
    BANK_USE(true, "Forbidden state");

    /**
     * flag
     */
    private final Boolean flag;
    /**
     * name
     */
    private final String name;

}
