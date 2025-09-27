package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.EvaluationsQueryDto;
import cn.kmbeast.pojo.entity.Evaluations;
import cn.kmbeast.pojo.vo.CommentChildVO;
import cn.kmbeast.pojo.vo.CommentParentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Comment persistence interface
 */
public interface EvaluationsMapper {

    /**
     * Search for all comments under the specified content
     *
     * @param contentId   Content ID
     * @param contentType Content type
     * @return List<CommentParentVO>
     */
    List<CommentParentVO> getParentComments(@Param(value = "contentId") Integer contentId,
                                            @Param(value = "contentType") String contentType);

    /**
     * Page search for comments
     *
     * @param evaluationsQueryDto parameter
     * @return List<CommentParentVO>
     */
    List<CommentChildVO> query(EvaluationsQueryDto evaluationsQueryDto);

    /**
     * Page query total number of comments
     *
     * @param evaluationsQueryDto parameter
     * @return List<CommentParentVO>
     */
    Integer queryCount(EvaluationsQueryDto evaluationsQueryDto);

    /**
     * Search for all second level comments
     *
     * @param ids ID List
     * @return List<Integer>
     */
    List<Integer> selectChildComments(@Param(value = "ids") List<Integer> ids);

    /**
     * Batch Delete
     *
     * @param ids ID List
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

    /**
     * Query the number of specified comments
     *
     * @param contentId   Content ID
     * @param contentType Content type
     * @return Integer
     */
    Integer totalCount(Integer contentId, String contentType);

    /**
     * Comment added
     *
     * @param evaluations Comment information entity
     */
    void save(Evaluations evaluations);

    /**
     * Comment modification
     *
     * @param evaluations Comment entity
     */
    void update(Evaluations evaluations);

}
