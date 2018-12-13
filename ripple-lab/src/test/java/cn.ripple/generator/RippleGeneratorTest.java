package cn.ripple.generator;

import cn.ripple.dao.mapper.TableMapper;
import cn.ripple.entity.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: Edwin
 * @Date: 2018/12/12 10:04
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RippleGeneratorTest {
    @Autowired
    TableMapper tableMapper;
    @Test
    public void test() throws IOException {
        System.out.println( System.getProperty("java.library.path"));
        System.loadLibrary("libarcsoft_face_engine_jni");
//        List<Table> tables = tableMapper.listTable();
//        for(Table table:tables){
//            RippleGenerator.generateCode(table.getTableName());
//        }
    }

}
