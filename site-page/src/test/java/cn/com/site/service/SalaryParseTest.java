package cn.com.site.service;

import cn.com.site.page.App;
import cn.com.site.page.service.impl.SalaryJsonParse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: SalaryParseTest
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-11-14 23:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class SalaryParseTest {

    @Autowired
    private SalaryJsonParse salaryJsonParse;

    @Test
    public void testOfferParse(){
        salaryJsonParse.parseSalaryOffer();
    }
}
