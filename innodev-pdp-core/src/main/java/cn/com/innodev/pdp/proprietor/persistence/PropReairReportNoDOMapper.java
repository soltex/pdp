package cn.com.innodev.pdp.proprietor.persistence;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.proprietor.persistence.object.PropReairReportNoDO;

@MyBatisRepository
public interface PropReairReportNoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropReairReportNoDO record);

    int insertSelective(PropReairReportNoDO record);
    
    int deleteAll();
    
}