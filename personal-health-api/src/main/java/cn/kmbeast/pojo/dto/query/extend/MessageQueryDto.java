package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageQueryDto extends QueryDto {

    /**
     * user id

     */
    private Integer userId;
    /**
     * message type

     */
    private Integer messageType;
    /**
     * Message content

     */
    private String content;
    /**
     * Have you read
     */
    private Boolean isRead;

}
