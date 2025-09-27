package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Comment entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Evaluations {
    /**
     * Primary key
     */
    private Integer id;

    /**
     * Parent comment ID
     */
    private Integer parentId;

    /**
     * Reviewer ID
     */
    private Integer commenterId;

    /**
     * Respondent ID
     */
    private Integer replierId;

    /**
     * Content type
     */
    private String contentType;

    /**
     * Comment content
     */
    private String content;

    /**
     * Content ID
     */
    private Integer contentId;

    /**
     * Like information list (separated by ",")
     */
    private String upvoteList;

    /**
     * Comment Time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
