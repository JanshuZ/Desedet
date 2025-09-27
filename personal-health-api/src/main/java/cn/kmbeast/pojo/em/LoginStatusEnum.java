package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Login status enumeration
 */
@Getter
@AllArgsConstructor
public enum LoginStatusEnum {

    USE(false, "Can log in"),
    BANK_USE(true, "Login status abnormal");

    /**
     * code
     */
    private final Boolean flag;
    /**
     * name
     */
    private final String name;

}
