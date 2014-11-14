package cn.com.innodev.pdp.proprietor.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.vanstone.dal.mybatis.MyBatisRepository;

import cn.com.innodev.pdp.proprietor.persistence.object.PropQACommentDO;

@MyBatisRepository
public interface PropQACommentDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(PropQACommentDO record);

    int insertSelective(PropQACommentDO record);

    PropQACommentDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PropQACommentDO record);

    int updateByPrimaryKeyWithBLOBs(PropQACommentDO record);

    int updateByPrimaryKey(PropQACommentDO record);
    
    List<PropQACommentDO> selectByQaId(@Param("qaId")String qaId, RowBounds rowBounds);
    
    int selectCountByQaId(@Param("qaId")String qaId);
    
}