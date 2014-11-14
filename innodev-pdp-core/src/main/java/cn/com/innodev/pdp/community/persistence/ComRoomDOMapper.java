package cn.com.innodev.pdp.community.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComRoomDO;

@MyBatisRepository
public interface ComRoomDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComRoomDO record);

    int insertSelective(ComRoomDO record);

    ComRoomDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComRoomDO record);

    int updateByPrimaryKey(ComRoomDO record);
    
    List<ComRoomDO> selectByBuildingId(@Param("buildingId")Integer buildingId);
    
    ComRoomDO selectByBuildingId_RoomNo(@Param("buildingId")Integer buildingId, @Param("roomNo")String roomNo);
    
    ComRoomDO selectByBuildingId_RoomNo_NotSelf(@Param("roomNo")String roomNo, @Param("id")Integer id);
    
}