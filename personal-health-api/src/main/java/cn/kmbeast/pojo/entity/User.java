package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User Entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * User ID
     */
    private Integer id;

    /**
     * user account
     */
    private String userAccount;

    /**
     * User nickname
     */
    private String userName;

    /**
     * Password
     */
    private String userPwd;

    /**
     * User profile picture
     */
    private String userAvatar;

    /**
     * User Mailbox
     */
    private String userEmail;

    /**
     * ROLE
     */
    private Integer userRole;

    /**
     * Login status (0: available; 1: unavailable)
     */
    private Boolean isLogin;

    /**
     * Forbidden state (0: available; 1: unavailable)
     */
    private Boolean isWord;

    /**
     *User registration time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
