package cn.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
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
     * Registration time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
