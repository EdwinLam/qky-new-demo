package cn.ripple.dao.mapper;

import cn.ripple.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Edwin
 * @Date: 2018/12/12 11:13
 * @Description:
 */
@Mapper
public interface TableMapper {
    @Select("select TABLE_Name tableName,TABLE_COMMENT tableComment from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Table> listTable();

    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<Map> listTableColumn(String tableName);
}
