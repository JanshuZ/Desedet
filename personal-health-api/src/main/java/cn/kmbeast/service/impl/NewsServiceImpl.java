package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.NewsMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsQueryDto;
import cn.kmbeast.pojo.entity.News;
import cn.kmbeast.pojo.vo.NewsVO;
import cn.kmbeast.service.NewsService;
import cn.kmbeast.utils.PathUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Health Information Business Logic
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    /**
     * New Health Information
     *
     * @param news parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(News news) {
        news.setCreateTime(LocalDateTime.now());
        newsMapper.save(news);
        return ApiResult.success();
    }

    /**
     * Delete health information
     *
     * @param ids parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Long> ids) {
        newsMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * Health information modification
     *
     * @param news parameter
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(News news) {
        newsMapper.update(news);
        return ApiResult.success();
    }

    /**
     * Health information inquiry
     *
     * @param NewsQueryDto Universal Response Body
     * @return Result<List < NewsVO>>
     */
    @Override
    public Result<List<NewsVO>> query(NewsQueryDto NewsQueryDto) {
        List<NewsVO> NewsList = newsMapper.query(NewsQueryDto);
        Integer totalCount = newsMapper.queryCount(NewsQueryDto);
        return PageResult.success(NewsList, totalCount);
    }


    //Retrieve news based on news ID
    @Override
    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }


    @Override
    public List<News> findByReviewStatus(Integer reviewStatus) {
        return newsMapper.findByReviewStatus(reviewStatus);
    }

    @Override
    public boolean deleteNewsWithCover(Integer newsId, String coverPath) {
        //Delete cover image
        boolean coverDeleted = deleteCoverFile(coverPath);

        //Delete news records from the database
        int rowsAffected = newsMapper.deleteNewsById(newsId);

        //Only when both the image and database records are successfully deleted, return true
        return coverDeleted && rowsAffected > 0;
    }

    /**
     *Cover file deletion logic
     */
    private boolean deleteCoverFile(String coverPath) {
        if (coverPath != null && !coverPath.isEmpty()) {
            //Extract file name
            String filename = coverPath.substring(coverPath.lastIndexOf("=") + 1);

            //File Path
            File fileDir = new File(PathUtils.getClassLoadRootPath() + "/pic");
            File file = new File(fileDir, filename);

            //Delete file
            return file.exists() && file.delete();
        }
        return true; //When there is no cover path, it is considered successful
    }

    public void approveNews(Integer id) {
        newsMapper.updateReviewStatus(id);
    }
}
