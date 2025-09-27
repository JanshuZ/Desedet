package cn.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Parent level comments
 */
@Data
public class CommentParentVO {

    /**
     * Comment ID
     */
    private Integer id;
    /**
     * user id
     */
    private Integer userId;
    /**
     * user name
     */
    private String userName;
    /**
     * User profile picture
     */
    private String userAvatar;
    /**
     * Comment content
     */
    private String content;
    /**
     * Reply box displays status
     */
    private Boolean showReplyInput;
    /**
     * The total number of subLevel comments owned
     */
    private Integer childTotal;
    /**
     * Has the user already liked it
     */
    private Boolean upvoteFlag;
    /**
     * Like List
     */
    private String upvoteList;
    /**
     * Like count
     */
    private Integer upvoteCount;
    /**
     * Comment Time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * Sub level comments
     */
    private List<CommentChildVO> commentChildVOS;

}
