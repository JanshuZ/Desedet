package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 资讯的收藏实体
 */
@Data
public class NewsSave {
    /**mvn clean install
     * ID
     */
    private Integer id;
    /**
     * user id

     */
    private Integer userId;
    /**
     * Health Information ID
     */
    private Integer newsId;
    /**
     * Collection time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
