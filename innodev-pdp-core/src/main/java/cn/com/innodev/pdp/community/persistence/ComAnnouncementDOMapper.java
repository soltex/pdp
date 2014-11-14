package cn.com.innodev.pdp.community.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComAnnouncementDO;

@MyBatisRepository
public interface ComAnnouncementDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(ComAnnouncementDO record);

    int insertSelective(ComAnnouncementDO record);

    ComAnnouncementDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComAnnouncementDO record);

    int updateByPrimaryKeyWithBLOBs(ComAnnouncementDO record);

    int updateByPrimaryKey(ComAnnouncementDO record);
    
    List<ComAnnouncementDO> selectPageBreak(@Param("communityId") String communityId, RowBounds rowBounds);
    
    int selectCount(@Param("communityId") String communityId);
}