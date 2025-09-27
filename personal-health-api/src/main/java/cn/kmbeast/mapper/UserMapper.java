package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *User Persistent Interface
 */
public interface UserMapper {


    /**
     * User Information Addition
     *
     * @param userInsert user information
     * @return int 受影响行数
     */
    int insert(User userInsert);

    /**
     * Page wise query of user information
     *
     * @param userQueryDto Paging Universal Response Body
     * @return List<User>
     */
    List<User> query(UserQueryDto userQueryDto);

    /**
     * Query the total number of records that meet the pagination query requirements
     *
     * @param userQueryDto Paging query parameters
     * @return int Total number of data
     */
    int queryCount(UserQueryDto userQueryDto);

    /**
     * Update user information
     *
     * @param user user information
     * @return int Number of affected rows
     */
    int update(User user);

    /**
     * Batch delete user information
     *
     * @param ids User ID Collection
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

    /**
     * Search for users based on nonempty query information
     *
     * @param user parameter
     * @return User
     */
    User getByActive(User user);

}
