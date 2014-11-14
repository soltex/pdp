package cn.com.innodev.pdp.proprietor.persistence;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorDO;
import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorDOWithBLOBs;

import com.vanstone.dal.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PropProprietorDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(PropProprietorDOWithBLOBs record);

    int insertSelective(PropProprietorDOWithBLOBs record);

    PropProprietorDOWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PropProprietorDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PropProprietorDOWithBLOBs record);

    int updateByPrimaryKey(PropProprietorDO record);
    
    int updateHead(@Param("id")String id, @Param("sysUpdateTime")Date sysUpdateTime, 
    		@Param("headerFileId")String headerFileId, @Param("headerFileExt")String headerFileExt,
    		@Param("headerFileWidth")Integer headerFileWidth, @Param("headerFileHeight")Integer headerFileHeight);
    
    PropProprietorDOWithBLOBs selectByEmail(@Param("email")String email);
    
    PropProprietorDOWithBLOBs selectByMobile(@Param("mobile")String mobile);
    
    PropProprietorDOWithBLOBs selectByOpenId(@Param("openId")String openId);
    
    PropProprietorDOWithBLOBs selectByMobile_Notself(@Param("id")String id, @Param("mobile")String mobile);
    
    List<PropProprietorDOWithBLOBs> selectByIds(@Param("ids")Collection<String> ids);
    
} 