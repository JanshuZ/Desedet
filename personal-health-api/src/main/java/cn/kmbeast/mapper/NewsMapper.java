package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.NewsQueryDto;
import cn.kmbeast.pojo.dto.query.extend.TagsQueryDto;
import cn.kmbeast.pojo.entity.News;
import cn.kmbeast.pojo.entity.Tags;
import cn.kmbeast.pojo.vo.NewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *Label persistence interface
 */
@Mapper
public interface NewsMapper {

    void save(News news);

    void update(News news);

    void batchDelete(@Param(value = "ids") List<Long> ids);

    List<NewsVO> query(NewsQueryDto newsQueryDto);

    Integer queryCount(NewsQueryDto newsQueryDto);

    //Search for news based on ID
    News getNewsById(@Param("id") Integer id); //@ Param is used here to mark parameters

    @Select("SELECT * FROM news WHERE review_status = #{reviewStatus}")
    List<News> findByReviewStatus(Integer reviewStatus);

    int deleteNewsById(@Param("id") Integer id);

    // Update the review status of news
    @Update("UPDATE news SET review_status = 1 WHERE id = #{id}")
    void updateReviewStatus(Integer id);

}
