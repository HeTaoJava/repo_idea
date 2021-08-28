import com.lagou.dao.TestMapper;
import com.lagou.domain.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-dao.xml")
public class findAllTest {
    @Autowired
    private TestMapper testMapper;
    @org.junit.Test
    public void findAll(){
        List<Test> allTest = testMapper.findAllTest();
        System.out.println(allTest);
    }
}
