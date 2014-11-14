package cn.com.innodev.pdp.proprietor.persistence;

import cn.com.innodev.pdp.proprietor.persistence.object.PropVisitorLogQRSceneDO;

public interface PropVisitorLogQRSceneDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropVisitorLogQRSceneDO record);

    int insertSelective(PropVisitorLogQRSceneDO record);
}