package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Message output parameter VO class
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageVO extends Message {

    /**
     * Sender Name
     */
    private String senderName;
    /**
     * Receiver profile picture
     */
    private String receiverAvatar;
    /**
     * Receiver Name
     */
    private String receiverName;
    /**
     * Comment content
     */
    private String evaluationsContent;

}
