package cn.com.innodev.pdp.community.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComBuildingDO;

@MyBatisRepository
public interface ComBuildingDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComBuildingDO record);

    int insertSelective(ComBuildingDO record);

    ComBuildingDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComBuildingDO record);

    int updateByPrimaryKeyWithBLOBs(ComBuildingDO record);

    int updateByPrimaryKey(ComBuildingDO record);
    
    ComBuildingDO selectByRoomNo(@Param("communityId")String communityId, @Param("buildingNo")String buildingNo);
    
    ComBuildingDO selectByRoomNo_NotSelf(@Param("id")Integer id, @Param("buildingNo")String buildingNo);
    
    List<ComBuildingDO> selectByCommunityId(@Param("communityId")String id);
    
}