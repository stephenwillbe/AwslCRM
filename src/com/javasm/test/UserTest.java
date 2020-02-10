import com.javasm.admin.dao.UserMapper;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Description: 测试用户实现的服务
 * @Author 陈嘉浩
 * @Date 2020/2/4
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class UserTest {

    @Resource
    private UserService us;

    @Resource
    private UserMapper ud;
    //测试获取单个用户信息
    @Test
    public void getUser(){
        System.out.println(us.getUser(888894));
    }

    //测试验证登陆信息
    @Test
    public void checkUser(){
        User user = new User("doninb","1234");
//        System.out.println(ud.selectUsers(user));
        System.out.println(us.checkUser(user));
    }
    //测试获取全部用户
    @Test
    public void getUsers(){
        System.out.println(us.selectUser());
    }

    //测试跟新用户
    @Test
    public void uptUser(){
        User orUser = us.getUser(888894);
        orUser.setUserAge(23);
        System.out.println(us.updateUser(orUser));
    }

    @Test
    public void getUserBy(){
        System.out.println(us.getUserByPhone("111"));
    }



}
