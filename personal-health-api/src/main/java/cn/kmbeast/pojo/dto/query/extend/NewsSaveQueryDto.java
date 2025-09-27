package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsSaveQueryDto extends QueryDto {

    /**
     * user id

     */
    private Integer userId;
    /**
     *
     * Health Information ID
     */
    private Integer newsId;

}
