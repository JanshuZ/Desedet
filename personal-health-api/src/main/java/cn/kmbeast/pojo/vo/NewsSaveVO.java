package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.NewsSave;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsSaveVO extends NewsSave {

    /**
     * user name

     */
    private String userName;
    /**
     * Health Information Cover
     */
    private String cover;
    /**
     * Health News Title
     */
    private String name;
    /**
     * Health information content
     */
    private String content;
    /**
     * tag
     */
    private String tagName;
    /**
     * Information release time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime newsCreateTime;

}
