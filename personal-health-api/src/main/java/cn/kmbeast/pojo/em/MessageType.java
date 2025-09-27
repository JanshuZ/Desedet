package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    //Interactive messages (comments being replied to, comments being liked by others), indicator messages, system notifications
    EVALUATIONS_BY_REPLY(1,"Comments"),
    EVALUATIONS_BY_UPVOTE(2,"Likes"),
    DATA_MESSAGE(3,"Indicator reminder"),
    SYSTEM_INFO(4,"System notification");

    /**
     * message type

     */
    private final Integer type;

    /**
     * Remarks for message type
     */
    private final String detail;

}
