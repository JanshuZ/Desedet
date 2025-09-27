package cn.kmbeast.pojo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Universal Response Body - pagination
 *
 * @param <T> generic

 * ']
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> extends Result<T> {

    /**
     * Paging response data
     */
    private T data;
    /**
     * Total number of records that meet the criteria
     */
    private Integer total;

    /**
     * Parameter construction
     *
     * @param code response code

     *
     */
    public PageResult(Integer code) {
        super(code, "query was successful");
    }

    /**
     * Feedback on pagination blood test results
     *
     * @param data  data source
     * @param total Total number of records
     * @param <T>   generic

     * @return <T>
     *
     */
    public static <T> Result<T> success(T data, Integer total) {
        PageResult<T> result = new PageResult<>(ResultCode.REQUEST_SUCCESS.getCode());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

}
