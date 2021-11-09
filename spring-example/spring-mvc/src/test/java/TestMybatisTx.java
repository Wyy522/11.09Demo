import com.easy.archiecture.config.DataConfig;
import com.easy.archiecture.config.ServletConfig;
import com.easy.archiecture.config.WebConfig;
import com.easy.archiecture.config.info.JdbcInfo;
import com.easy.archiecture.entity.User;
import com.easy.archiecture.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class,
        ServletConfig.class,
        DataConfig.class,
        JdbcInfo.class})
@WebAppConfiguration
public class TestMybatisTx {

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void testTx() {
        User u = new User();
        u.setName("tx_test");
        u.setAge(19);
        u.setPassword("123456");
        int result = userService.insertUserAndJoinClazz(u, 2);
        Assert.assertEquals(1, result);

    }
}
