package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.News;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsVO extends News {

    /**
     * Tag Name
     */
    private String tagName;

    private Integer reviewStatus;
}
