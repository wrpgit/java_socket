package com.wrpxcx.mapper;

import com.wrpxcx.entity.LastMessage;
import com.wrpxcx.entity.LastMessageDetail;
import com.wrpxcx.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    //添加新消息
    int addMessage(Message message);

    //查询某两个人的消息
    List<Message> getMessagesByUserId(@Param("fromId") String fromId,
                                      @Param("toId") String toId);


    //添加消息列表中的 信息， 如果两个人是第一次发消息，则调用这个
    int addLastMessage(LastMessage lastMessage);

    //每当人发消息时 ，都会更新列表
    int updateLastMessage(LastMessage lastMessage);

    //查询消息列表
    List<LastMessageDetail> getMessageListById(@Param("userId") String userId);

    //查询指定某个 最后一条通信
    LastMessageDetail getMessageListByFromAndTo(@Param("fromUserId") String fromUserId,
                                                @Param("toUserId") String toUserId);

    int zeroNotReadNum(@Param("fromUserId") String fromUserId,
                       @Param("toUserId") String toUserId);


}
