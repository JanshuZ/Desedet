package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Message entity
 */
@Data
public class Message {
    /**
     * Primary key

ID
     */
    private Integer id;
    /**
     * Message content

     */
    private String content;
    /**
     * message type

     */
    private Integer messageType;
    /**
     * Receiver User ID
     */
    private Integer receiverId;
    /**
     * Sender User ID
     */
    private Integer senderId;
    /**
     * Set the message to 0 when reading and adding: false ，1：true
     */
    private Boolean isRead;
    /**
     * Content ID  If the message type belongs to interactive related data (comments have been commented on by others, your comment has been liked by others)
     */
    private Integer contentId;
    /**
     * Message sending time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
