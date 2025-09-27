package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

    NO_AUDIT(1, "Unaudited"),
    HAVE_AUDIT(2, "Already reviewed");

    /**
     * status
     */
    private final Integer status;
    /**
     * Status explanation
     */
    private final String detail;


}
