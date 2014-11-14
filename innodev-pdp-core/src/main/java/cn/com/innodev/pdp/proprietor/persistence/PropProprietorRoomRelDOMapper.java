package cn.com.innodev.pdp.proprietor.persistence;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorRoomRelDOKey;

@MyBatisRepository
public interface PropProprietorRoomRelDOMapper {
    int deleteByPrimaryKey(PropProprietorRoomRelDOKey key);

    int insert(PropProprietorRoomRelDOKey record);

    int insertSelective(PropProprietorRoomRelDOKey record);
    
    int selectByRoomId(@Param("roomId")Integer roomId);
    
    int deleteByProprietorId(@Param("proprietorId")String proprietorId);
    
    int selectCountByRoomId(@Param("roomId")Integer roomId);
    
}