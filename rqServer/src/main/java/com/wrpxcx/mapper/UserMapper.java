package com.wrpxcx.mapper;

import com.wrpxcx.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: wrp
 * @TODO: 用户操作访问数据库接口
 * @time: 2020-05-16 08:34
 **/
public interface UserMapper {

    User getUserById(String userId);

    int addUser(User user);

    int updateUser(User user);

    int addTbLogin(@Param("userId") String id,
                   @Param("password") String password);

    int checkUser(@Param("userId") String id,
                   @Param("password") String password);

    int updateUserPassword(@Param("userId") String id,
                  @Param("password") String password);

}
