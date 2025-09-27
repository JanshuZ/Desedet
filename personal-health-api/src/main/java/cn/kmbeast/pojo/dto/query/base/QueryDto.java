package cn.kmbeast.pojo.dto.query.base;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Query parameter receiving entity class base class, containing four basic parameters that can be extended when used
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QueryDto {
    /**
     * current page
     */
    private Integer current;
    /**
     * Page data size
     */
    private Integer size;
    /**
     * Start Time
     */
    private LocalDateTime startTime;
    /**
     * End Time
     */
    private LocalDateTime endTime;
}

