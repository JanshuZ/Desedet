package cn.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentChildVO {
    /**
     * Comment ID
     */
    private Integer id;
    /**
     * Parent comment ID
     */
    private Integer parentId;
    /**
     *Reviewer ID
     */
    private Integer userId;
    /**
     *Commenter username
     */
    private String userName;
    /**
     * Commenter User Avatar
     */
    private String userAvatar;

    /**
     * Respondent ID
     */
    private Integer replierId;

    /**
     * Respondent username
     */
    private String replierName;

    /**
     * Respondent profile picture
     */
    private String replierAvatar;

    /**
     * Comment content
     */
    private String content;

    /**
     *Comment reply status
     */
    private Boolean replyInputStatus;

    /**
     * Like List
     */
    private String upvoteList;

    /**
     * Has the user already liked it
     */
    private Boolean upvoteFlag;

    /**
     * Like count
     */
    private Integer upvoteCount;

    /**
     * Report volume
     */
    private Integer reportsNum;
    /**
     * Content type
     */
    private String contentType;

    /**
     * Comment Time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
