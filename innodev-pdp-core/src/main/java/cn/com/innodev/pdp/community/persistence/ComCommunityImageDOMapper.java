package cn.com.innodev.pdp.community.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComCommunityImageDO;

@MyBatisRepository
public interface ComCommunityImageDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(ComCommunityImageDO record);

    int insertSelective(ComCommunityImageDO record);

    ComCommunityImageDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComCommunityImageDO record);

    int updateByPrimaryKey(ComCommunityImageDO record);
    
    List<ComCommunityImageDO> selectByCommunityId(@Param("communityId")String communityId);
    
}