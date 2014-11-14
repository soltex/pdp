package cn.com.innodev.pdp.community.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComCommunityDO;

@MyBatisRepository
public interface ComCommunityDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(ComCommunityDO record);

    int insertSelective(ComCommunityDO record);

    ComCommunityDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComCommunityDO record);

    int updateByPrimaryKeyWithBLOBs(ComCommunityDO record);

    int updateByPrimaryKey(ComCommunityDO record);
    
    int updateQR(@Param("communityId")String communityId,@Param("sysUpdateTime")Date sysUpdateTime,@Param("qrFileId")String qrFileId,@Param("qrFileExt")String qrFileExt,
    		@Param("qrFileWidth")Integer qrFileWidth,@Param("qrFileHeight")Integer qrFileHeight);
    
    List<String> selectByKey(@Param("key") String key, RowBounds rowBounds);
    
    int selectCountByKey(@Param("key")String key);
    
}