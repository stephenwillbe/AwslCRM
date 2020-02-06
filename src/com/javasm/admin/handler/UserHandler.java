package com.javasm.admin.handler;

import com.alibaba.fastjson.JSON;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户控制类
 * @Author 陈嘉浩
 * @Date 2020/2/4
 * @Version 1.0
 **/
@Controller
public class UserHandler {

    @Resource
    private UserService us;

    /*
     * @Description //跳转到登陆页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/loginout")
    public String gologin(){
        return "loginout";
    }
    /*
     * @Description //跳转到首页
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/home")
    public String gohome(){
        return "forward:WEB-INF/view/home.html";
    }
    /*
     * @Description //跳转到用户列表页
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/userList")
    public String goUserlist(){
        return "forward:WEB-INF/view/user_list.html";
    }

    /*
     * @Description //跳转到新增用户页
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/userAdd")
    public String goAddUser(){
        return "forward:WEB-INF/view/user_add.html";
    }

    /*
     * @Description //跳转到新增用户页
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/perList")
    public String goPerList(){
        return "forward:WEB-INF/view/per_list.html";
    }

    /*
     * @Description //验证账号并登陆跳转到主页
     * @Param 请求对象，用户输入数据
     * @return 验证后跳转的页面
     **/
    @RequestMapping("/checklog")
    @ResponseBody
    public String checkLog(HttpServletRequest request, User inputUser){
        if(request!=null&&inputUser!=null&&!"".equals(inputUser)){
            Map<String,Object> returnMap = new HashMap<>();
            HttpSession httpSession = request.getSession();
            User verfiUser = us.checkUser(inputUser);
            if(verfiUser!=null){
                httpSession.setAttribute("LOGIN_USER",verfiUser);
                returnMap.put("Code","200");
                returnMap.put("reMes","success");
                returnMap.put("User",verfiUser);
            }else{
                returnMap.put("Code","500");
                returnMap.put("reMes","failure");
            }
            String returnMessage = JSON.toJSONString(returnMap);
            return returnMessage;
        }else{
            return "failure";
        }
    }

    /*
     * @Description //退出登录，并清除session
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/exit")
    public String goOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("LOGIN_USER");
        return "redirect:loginout";
    }

    @RequestMapping("/getAllUsers")
    public String getUserList(){
        return null;
    }

}
