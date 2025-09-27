package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User role enumeration
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN(1, "管理员"),
    USER(2, "用户");

    /**
     * Role Code
     */
    private final Integer role;
    /**
     * 角色名
     */
    private final String name;

    /**
     * Obtain character names from character encoding
     *
     * @param role Role Code
     * @return String role name
     */
    public static String ROLE(Integer role) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getRole().equals(role)) {
                return value.name;
            }
        }
        return null;
    }

}
