package cn.kmbeast.pojo.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    /**
     *user name
     */
    private String userName;
    /**
     * account number
     */
    private String userAccount;
    /**
     * password
     */
    private String userPwd;
    /**
     * User Mailbox
     */
    private String userEmail;
    /**
     * User profile picture
     */
    private String userAvatar;
}
