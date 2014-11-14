package cn.com.innodev.pdp.admin.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.innodev.pdp.admin.persistence.object.AuthAdminDO;

import com.vanstone.dal.mybatis.MyBatisRepository;

@MyBatisRepository
public interface AuthAdminDOMapper {
    int deleteByPrimaryKey(String id);
    
    int insert(AuthAdminDO record);

    int insertSelective(AuthAdminDO record);

    AuthAdminDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AuthAdminDO record);

    int updateByPrimaryKey(AuthAdminDO record);
    
    AuthAdminDO selectByAdminName(@Param("adminName")String adminName);
    
    List<AuthAdminDO> selectAll(RowBounds rowBounds);
    
    int selectAllTotal();
    
}