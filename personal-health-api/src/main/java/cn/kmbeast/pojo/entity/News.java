package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Health Information Entity
 */
@Data
public class News {
    /**
     * Primary key ID
     */
    private Integer id;
    /**
     * News Title
     */
    private String name;
    /**
     * Content of Information
     */
    private String content;
    /**
     * Tag ID
     */
    private Integer tagId;
    /**
     * cover
     */
    private String cover;
    /**
     * Reader's ID List, divided by ","
     */
    private String readerIds;
    /**
     * is top
     */
    private Boolean isTop;
    /**
     * Release time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * Review status: 0 pending review, 1 approved review, 2 failed review
     */
    private Integer reviewStatus;
}
