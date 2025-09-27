package cn.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Comment VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationsVO {
    /**
     * total
     */
    private Integer count;
    /**
     * Comment data
     */
    private List<CommentParentVO> data;
}
