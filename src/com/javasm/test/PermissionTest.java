import com.javasm.admin.dao.PermissionMapper;
import com.javasm.admin.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Description: 权限功能测试
 * @Author 陈嘉浩
 * @Date 2020/2/7
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class PermissionTest {

    @Resource
    private PermissionService ps;

    @Resource
    private PermissionMapper pd;

    @Test
    public void getPer(){
        System.out.println(pd.selectByPrimaryKey(10));
    }
}
