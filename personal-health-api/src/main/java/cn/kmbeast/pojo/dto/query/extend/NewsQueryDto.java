package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsQueryDto extends QueryDto {

    /**
     * Information Name

     */
    private String name;
    /**
     * TAG ID
     */
    private Integer tagId;
    /**
     * recommend it
     */
    private Boolean isTop;

    private Integer reviewStatus;
}
