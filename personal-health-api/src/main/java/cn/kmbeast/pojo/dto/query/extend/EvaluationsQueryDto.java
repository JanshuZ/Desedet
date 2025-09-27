package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Comment query parameter Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EvaluationsQueryDto extends QueryDto {

    /**
     * Content type
     */
    private String contentType;
    /**
     * The content of the comment
     */
    private String content;

}
