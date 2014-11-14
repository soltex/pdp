package cn.com.innodev.pdp.community.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComQADO;

@MyBatisRepository
public interface ComQADOMapper {
    int deleteByPrimaryKey(String id);

    int insert(ComQADO record);

    int insertSelective(ComQADO record);

    ComQADO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComQADO record);

    int updateByPrimaryKeyWithBLOBs(ComQADO record);

    int updateByPrimaryKey(ComQADO record);
    
    List<ComQADO> selectByCommunityId(@Param("communityId")String communityId, RowBounds rowBounds);
    
    int selectCountByCommunityId(@Param("communityId")String communityId);
    
}