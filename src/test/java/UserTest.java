import com.sdhery.module.helper.ServiceManager;
import com.sdhery.module.user.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-16
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/spring/*.xml")
public class UserTest extends AbstractJUnit4SpringContextTests {


    @Test
    public void testRun() {
        try {
            System.out.println("----" + ServiceManager.sysUserService.getSysUserByKey("root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
