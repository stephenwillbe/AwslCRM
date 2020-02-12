package com.javasm.admin.handler;

import com.alibaba.fastjson.JSON;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import com.javasm.util.RedisService;
import com.javasm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 系统控制层，跳转和登陆登出等
 * @Author 陈嘉浩
 * @Date 2020/2/6
 * @Version 1.0
 **/
@Controller
public class AdminHandler {

    @Resource
    private UserService us;

    @Resource
    private RedisService rs;

    private String userinfoKey="userinfo:";

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
     * @Description ////改造登录代码（当用户首次登录时，传过来手机号和验证码，先比较验证码正确与否；正确的话，去根据手机号查询redis数据库，
	// 如果查到用户信息，则登陆；如果查不到，去mysql数据库根据手机号查询，如果mysql也查不到，则表示未注册；
	// 未注册，需要自动注册,需要在mysql数据库中添加数据，需要在redis数据库中添加数据.）
     * @Param 请求对象，用户输入的手机号，验证码
     * @return java.lang.String
     **/
    @RequestMapping("/checklog2")
    @ResponseBody
    public String checkLog2(HttpServletRequest request,String userPhone,String verCode){
        if(StringUtil.isMobile(userPhone)&&StringUtil.isNotEmpty(userPhone)){
            //校验验证码
            String getVer = rs.get("verCode:"+userPhone);
            Long ttl = rs.ttl("verCode:" + userPhone);
            if(getVer==null||ttl<0){
                return "NoneKey";
            }
            if(verCode.equals(getVer)){
                //根据手机号查询用户信息
                String key = userinfoKey+userPhone;
                String userinfoStr = rs.get(key);
                HttpSession httpSession = request.getSession();
                if(userinfoStr==null){
                    //redis查不到，检查系统的数据库
                    User user = us.getUserByPhone(userPhone);
                    if(user!=null){
                        //设置到redis里
                        rs.set(key, JSON.toJSONString(user));
                        //保存用户信息到session
                        httpSession.setAttribute("LOGIN_USER",user);
                        return "success";
                    }else{
                        //系统数据库里没用该用户，自动注册
                        User newUser = us.registUser(userPhone);
                        if(newUser!=null){
                            //设置到redis里
                            rs.set(key, JSON.toJSONString(newUser));
                            //保存用户信息到session
                            httpSession.setAttribute("LOGIN_USER",newUser);
                            return "success";
                        }
                    }
                }else{
                    //redis里有用户数据，直接登陆，保存session
                    //userinfostr转对象
                    httpSession.setAttribute("LOGIN_USER", JSON.parseObject(userinfoStr,User.class));
                    return "success";
                }
            }else {
                //验证码错误
                return "VerErro";
            }
        }
        //号码格式错误
        return "PhoneErro";
    }

    /*
     * @Description //前端传来的手机号--生成6位随机数字--保存到redis里--格式:手机号：验证码--设置有效时间--返回是否发送成功信息
     * @Param 用户登录的手机
     * @return java.lang.String
     **/
    @RequestMapping("/sendVerCode")
    @ResponseBody
    public String getVerFi(String userPhone){
        if(StringUtil.isNotEmpty(userPhone)&&StringUtil.isMobile(userPhone)){
            String randNum = StringUtil.randomStr(6);
            System.out.println(randNum);
            //设置验证码有效时间为5分钟
            rs.setex("verCode:"+userPhone,300,randNum);
            return "success";
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

}
