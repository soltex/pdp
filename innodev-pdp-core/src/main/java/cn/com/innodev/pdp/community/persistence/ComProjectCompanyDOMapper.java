package cn.com.innodev.pdp.community.persistence;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.community.persistence.object.ComProjectCompanyDO;

@MyBatisRepository
public interface ComProjectCompanyDOMapper {
    int deleteByPrimaryKey(String communityId);

    int insert(ComProjectCompanyDO record);

    int insertSelective(ComProjectCompanyDO record);

    ComProjectCompanyDO selectByPrimaryKey(String communityId);

    int updateByPrimaryKeySelective(ComProjectCompanyDO record);

    int updateByPrimaryKey(ComProjectCompanyDO record);
    
    int updateLogoFileInfo(@Param("communityId")String communityId, 
    		@Param("logoFileId")String logoFileId, 
    		@Param("logoFileExt")String logoFileExt, 
    		@Param("logoFileWidth")Integer logoFileWidth, @Param("logoFileHeight")Integer logoFileHeight);
    
}