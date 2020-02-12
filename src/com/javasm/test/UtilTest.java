import com.javasm.util.StringUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Description: 测试工具
 * @Author 陈嘉浩
 * @Date 2020/2/5
 * @Version 1.0
 **/
public class UtilTest {

    @Test
    public void getDate(){
        System.out.println(new Date().getTime());
    }

    @Test
    public void getRanNum(){
        System.out.println(StringUtil.randomStr(6));
    }
    @Test
    public void verfiPhone(){
        System.out.println(StringUtil.isMobile("1360030397"));
    }


}
