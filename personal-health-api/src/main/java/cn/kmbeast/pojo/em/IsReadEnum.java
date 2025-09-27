package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsReadEnum {

    READ_OK(true, "READ"),
    READ_NO(false, "UNREAD");

    /**
     * Has it been read
     */
    private final Boolean status;
    /**
     * describe
     */
    private final String detail;

}
