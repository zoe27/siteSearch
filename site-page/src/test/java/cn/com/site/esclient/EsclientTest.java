package cn.com.site.esclient;

import cn.com.site.es.esUtil.ESClient;
import cn.com.site.es.util.ESConstant;
import cn.com.site.page.App;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: EsclientTest
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-03-01 16:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class EsclientTest {

//    @Autowired
//    private ESClient esClient;

    @Test
    public void testRandomData(){
        //esClient.getRandomData(ESConstant.ES_SITE_INFO.INDEX.getInfo(), ESConstant.ES_SITE_INFO.TYPE.getInfo(), 50);
    }
}
