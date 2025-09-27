package cn.kmbeast.pojo.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
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
}
