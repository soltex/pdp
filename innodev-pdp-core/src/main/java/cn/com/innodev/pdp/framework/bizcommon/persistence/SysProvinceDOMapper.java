package cn.com.innodev.pdp.framework.bizcommon.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.framework.bizcommon.persistence.object.QuerySysProvinceResultMap;
import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysProvinceDO;

@MyBatisRepository
public interface SysProvinceDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysProvinceDO record);

    int insertSelective(SysProvinceDO record);

    SysProvinceDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysProvinceDO record);

    int updateByPrimaryKey(SysProvinceDO record);
    
    List<SysProvinceDO> selectAll();
    
    List<QuerySysProvinceResultMap> selectAllWithCityCount();
    
    SysProvinceDO selectByTitle(String title);
    
    SysProvinceDO selectByTitle_NotSelf(@Param("id")Integer id, @Param("title")String title);
    
}