package cn.com.innodev.pdp.proprietor.persistence;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.proprietor.persistence.object.PropValidatecodeDO;

@MyBatisRepository
public interface PropValidatecodeDOMapper {
    int deleteByPrimaryKey(String mobile);

    int insert(PropValidatecodeDO record);

    int insertSelective(PropValidatecodeDO record);

    PropValidatecodeDO selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(PropValidatecodeDO record);

    int updateByPrimaryKey(PropValidatecodeDO record);
    
    int deleteAll();
    
}