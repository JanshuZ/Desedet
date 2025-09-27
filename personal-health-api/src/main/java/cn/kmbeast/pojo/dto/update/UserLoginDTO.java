package cn.kmbeast.pojo.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    /**
     * account number
     */
    private String userAccount;
    /**
     * password
     */
    private String userPwd;
}
