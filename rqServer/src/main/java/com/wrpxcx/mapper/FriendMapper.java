package com.wrpxcx.mapper;

import com.wrpxcx.entity.Friend;
import com.wrpxcx.entity.FriendDetail;
import com.wrpxcx.entity.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: wrp
 * @TODO: 好友操作的接口  分组管理也放在了这
 * @time: 2020-05-16 09:33
 **/
public interface FriendMapper {

    List<FriendDetail> getFriendById(@Param("userId") String userId);

    int addFriend(Friend friend);

    //查询指定好友的信息
    FriendDetail getFriendByMyIdAndFriendId(@Param("myId") String myId, @Param("friendId") String friendId);

    //删除好友
    int removeFriend(@Param("myId") String myId, @Param("friendId") String friendId);

    //修改好友分组
    int changeFriendGroup(Friend friend);

    //查看是否已经是好友 防止重复添加
    int checkFriend(Friend friend);

    //添加新分组
    int addGroup(Group group);

    //删除分组
    int removeGroup(String groupId);

    //查询某个用户的所有分组
    List<Group> getGroupByUserId(@Param("userId") String userId);

}
