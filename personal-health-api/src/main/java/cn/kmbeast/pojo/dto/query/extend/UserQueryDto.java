package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User queries DTO parameters
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends QueryDto {
    /**
     * User's account
     */
    private String userAccount;
    /**
     * User's name
     */
    private String userName;
    /**
     * User's email
     */
    private String userEmail;
    /**
     * User Roles
     */
    private Boolean role;
    /**
     * Can OR not log in
     */
    private Boolean isLogin;
    /**
     * banned from speaking
     */
    private Boolean isWord;
}
