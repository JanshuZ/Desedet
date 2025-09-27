package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsQueryDto;
import cn.kmbeast.pojo.entity.News;
import cn.kmbeast.pojo.vo.NewsVO;
import cn.kmbeast.service.NewsService;
import cn.kmbeast.utils.PathUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for Health News
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * Add new health news
     *
     * @param news The news data to add
     * @return Result<Void> General response
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody News news) {
        return newsService.save(news);
    }

    /**
     * Delete health news
     *
     * @param ids List of health news IDs to delete
     * @return Result<Void> General response
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        return newsService.batchDelete(ids);
    }

    /**
     * Update health news
     *
     * @param news The news data to update
     * @return Result<Void> Response
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody News news) {
        return newsService.update(news);
    }

    /**
     * Query health news with pagination
     *
     * @param newsQueryDto The query parameters
     * @return Result<List<NewsVO>> List of news view objects
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<NewsVO>> query(@RequestBody NewsQueryDto newsQueryDto) {
        return newsService.query(newsQueryDto);
    }

    /**
     * Save news and set it to pending review
     *
     * @param news The news data to save
     * @return ApiResult<?> The result of the save operation
     */
    @PostMapping("/saveNews")
    public ApiResult<?> saveNews(@RequestBody News news) {
        try {
            news.setReviewStatus(0); // Set to pending review status
            System.out.println("Attempting to save news: " + news); // Debug log
            newsService.save(news);
            System.out.println("News saved successfully: " + news); // Debug log
            return ApiResult.success("News has been submitted and is awaiting review");
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace
            return ApiResult.error("Failed to save news: " + e.getMessage());
        }
    }

    /**
     * Query news based on parameters
     *
     * @param params The query parameters (optional)
     * @return ResponseEntity<?> The response entity containing the news list or a message
     */
    @PostMapping("/queryNews")
    public ResponseEntity<?> queryNews(
            @RequestBody(required = false) Map<String, Object> params) {

        // Default to querying unreviewed news
        Integer reviewStatus = params != null && params.containsKey("reviewStatus")
                ? (Integer) params.get("reviewStatus")
                : 0;

        // Query the list of news that match the criteria
        List<News> newsList = newsService.findByReviewStatus(reviewStatus);

        if (newsList != null && !newsList.isEmpty()) {
            return ResponseEntity.ok(newsList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No news found matching the criteria");
        }
    }

    /**
     * Approve a news item
     *
     * @param id The ID of the news to approve
     * @return ApiResult<?> The result of the approval operation
     */
    @PutMapping("/approve/{id}")
    public ApiResult<?> approveNews(@PathVariable Integer id) {
        // Retrieve the news item
        News news = newsService.getNewsById(id);
        if (news != null && news.getReviewStatus() == 0) {
            // Update the review status to approved
            newsService.approveNews(id);
            return ApiResult.success("Review approved");
        } else {
            return ApiResult.error("News does not exist or has already been reviewed");
        }
    }

    /**
     * Reject a news item
     *
     * @param id The ID of the news to reject
     * @return ApiResult<?> The result of the rejection operation
     */
    @PutMapping("/reject/{id}")
    public ApiResult<?> rejectNews(@PathVariable Integer id) {
        // Retrieve the news item
        News news = newsService.getNewsById(id);
        if (news != null && news.getReviewStatus() == 0) {
            // Delete the news record and related cover image
            boolean isDeleted = newsService.deleteNewsWithCover(id, news.getCover());
            if (isDeleted) {
                return ApiResult.success("Review rejected, news and cover have been deleted");
            } else {
                return ApiResult.error("Error occurred while deleting news or cover");
            }
        } else {
            return ApiResult.error("News does not exist or has already been reviewed");
        }
    }
}
