package cn.com.innodev.pdp.framework.bizcommon.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysCityDO;

@MyBatisRepository
public interface SysCityDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCityDO record);

    int insertSelective(SysCityDO record);

    SysCityDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCityDO record);

    int updateByPrimaryKey(SysCityDO record);
    
    List<SysCityDO> selectByProvinceId(@Param("provinceId")Integer provinceId);
    
    List<SysCityDO> selectAll();
    
    SysCityDO selectByProvinceId_Title(@Param("provinceId")Integer provinceId, @Param("title")String title);
    
    SysCityDO selectByProvinceId_Title_NotSelf(@Param("provinceId")Integer provinceId, @Param("title")String title, @Param("id")Integer id);
}