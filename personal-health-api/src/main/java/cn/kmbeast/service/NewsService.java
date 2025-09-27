package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsQueryDto;
import cn.kmbeast.pojo.dto.query.extend.TagsQueryDto;
import cn.kmbeast.pojo.entity.News;
import cn.kmbeast.pojo.entity.Tags;
import cn.kmbeast.pojo.vo.NewsVO;

import java.util.List;

/**
 *Health Information Business Logic Interface
 */
public interface NewsService {

    Result<Void> save(News news);

    Result<Void> batchDelete(List<Long> ids);

    Result<Void> update(News news);

    Result<List<NewsVO>> query(NewsQueryDto newsQueryDto);
    News getNewsById(Integer id);


    List<News> findByReviewStatus(Integer reviewStatus);

    boolean deleteNewsWithCover(Integer newsId, String coverPath);

    void approveNews(Integer id);
}
