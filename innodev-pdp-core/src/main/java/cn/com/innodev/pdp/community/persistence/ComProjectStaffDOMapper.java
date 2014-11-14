package cn.com.innodev.pdp.community.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.innodev.pdp.community.persistence.object.ComProjectStaffDO;

import com.vanstone.dal.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ComProjectStaffDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComProjectStaffDO record);

    int insertSelective(ComProjectStaffDO record);

    ComProjectStaffDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComProjectStaffDO record);

    int updateByPrimaryKeyWithBLOBs(ComProjectStaffDO record);

    int updateByPrimaryKey(ComProjectStaffDO record);
    
    ComProjectStaffDO selectByAccountName(@Param("accountName")String accountName);
    
    int updateHead(@Param("id")Integer id, @Param("sysUpdateTime")Date sysUpdateTime,
    		@Param("headImageFileId")String headImageFileId,
    		@Param("headImageFileExt")String headImageFileExt, 
    		@Param("headImageFileWidth")Integer headImageFileWidth,
    		@Param("headImageFileHeight")Integer headImageFileHeight);
    
    List<ComProjectStaffDO> selectBy_CommunityId_StaffRole(@Param("communityId")String communityId, @Param("staffRole")Integer staffRole, 
    		RowBounds rowBounds);
    
    int selectCountBy_CommunityId_StaffRole(@Param("communityId")String communityId, @Param("staffRole")Integer staffRole);
    
}