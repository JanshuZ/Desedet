package cn.kmbeast.context;

import java.util.HashMap;
import java.util.Map;

/**
 *User Identity Support Device
 */
public class LocalThreadHolder {

    private static final ThreadLocal<Map<String, Integer>> USER_HOLDER = new ThreadLocal<>();

    /**
     * set user information
     *
     * @param userId   user id
     * @param userRole User Role
     */
    public static void setUserId(Integer userId, Integer userRole) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userRole", userRole);
        USER_HOLDER.set(map);
    }

    /**
     * Retrieve user ID
     *
     * @return Integer
     */
    public static Integer getUserId() {
        return USER_HOLDER.get().get("userId");
    }

    /**
     *Retrieve user roles
     *
     * @return Integer

     */
    public static Integer getRoleId() {
        return USER_HOLDER.get().get("userRole");
    }

    /**
     * Prevent memory overflow, end current thread, release resources
     *

     */
    public static void clear() {
        USER_HOLDER.remove();
    }

}
