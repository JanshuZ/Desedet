package cn.kmbeast.pojo.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Universal Response Body
 *
 * @param <T> generic
 */
@Setter
@Getter
public class ApiResult<T> extends Result<T> {

    /**
     *Response data
     */
    private T data;

    /**
     *Total data page, pagination usage
     */
    private Integer total;

    public ApiResult(Integer code) {
        super(code, "Success");
    }

    public ApiResult(Integer code, String msg) {
        super(code, msg);
    }

    public ApiResult(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public ApiResult(Integer code, String msg, T data, Integer total) {
        super(code, msg);
        this.data = data;
        this.total = total;
    }

    public ApiResult(T data, Integer total) {
        this.data = data;
        this.total = total;
    }

    /**
     * Default successful response, no data available
     *
     * @param <T> generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), "Success");
    }

    /**
     * Successful response, including data
     *
     * @param data data

     * @param <T>  generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), "Success", data);
    }

    /**
     * Successful response, including message and data
     *
     * @param msg message
     * @param data data

     * @param <T>  generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> success(String msg, T data) {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), msg, data);
    }

    /**
     * Successful response, paginated data
     *
     * @param data  Data list
     * @param total Total number of records
     * @param <T>   generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> success(T data, Integer total) {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), "Success", data, total);
    }

    /**
     * Successful response, including message
     *
     * @param msg message
     * @param <T> generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> success(String msg) {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), msg);
    }

    /**
     * error response
     *
     * @param msg Error Message

     * @param <T> generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> error(String msg) {
        return new ApiResult<>(ResultCode.REQUEST_ERROR.getCode(), msg);
    }

    /**
     * Error response, including data
     *
     * @param msg  Error Message

     * @param data erroneous data
     * @param <T>  generic

     * @return ApiResult
     */
    public static <T> ApiResult<T> error(String msg, T data) {
        return new ApiResult<>(ResultCode.REQUEST_ERROR.getCode(), msg, data);
    }
}
