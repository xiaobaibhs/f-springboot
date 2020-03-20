package cn.xiaobai.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import cn.xiaobai.admin.entity.FunctionInfo;
import cn.xiaobai.admin.entity.User;
import cn.xiaobai.admin.result.Result;
import cn.xiaobai.admin.result.ResultFactory;
import cn.xiaobai.admin.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(usernamePasswordToken);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        }
        /*  if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }*/
        /*User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            session.setAttribute("user", user);
            return new Result(200);
        }*/
    }

    @PostMapping("/api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addOrUpdate(user);

        return ResultFactory.buildSuccessResult(user);
    }

    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }
    
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
    /**
     * 本地登录
     * @param m
     * @return
     */
   // @RequestMapping("/login")
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Map login(HttpServletRequest req,@RequestBody Map m)
    {
        Map map = new HashMap();
        try
        {
            String name = (String)m.get("name");
            String password = (String)m.get("password");
           // password = Util.getMD5(password);
            int pos = name.indexOf(":");
            /*    if(pos<=0)
            {
                map.put("login", false);
                map.put("message", "输入登录名:"+name+" 错误,请检查");
                return map;
            }*/
           // String flag = name.substring(0,pos);
           // String loginId = name.substring(pos+1);
            //map = orgServ.login(flag,loginId,password);
            map.put("login", true);
            Boolean login = (Boolean)map.get("login");
            if(login)
            {
                String userId = (String)map.get("uid");
                // List<FunctionInfo> rtn = funcServ.getNavFuncTree(userId);
                List<FunctionInfo> rtn=new ArrayList<FunctionInfo>();
                List<FunctionInfo> child1=new ArrayList<FunctionInfo>();
            
                FunctionInfo f=new FunctionInfo();
                FunctionInfo  parent1=new FunctionInfo();
                f.setName("用户管理");
                f.setVisible(true);
                f.setPath("/gf/home");
                f.setUrl("/gf/home");
                child1.add(f);
                parent1.setName("系统管理");
                parent1.setUrl("");
                parent1.setChild(child1);
                
                FunctionInfo  parent2=new FunctionInfo();
                List<FunctionInfo> child2=new ArrayList<FunctionInfo>();
                FunctionInfo f2=new FunctionInfo();
                f2.setName("产品新建");
                f2.setVisible(true);
                f2.setPath("/gf/prdtlist");
                f2.setUrl("/gf/prdtlist");
                child2.add(f2);
                parent2.setName("产品管理");
                parent2.setUrl("");
                parent2.setChild(child2);
                rtn.add(parent1);
                rtn.add(parent2);
             
               map.put("navList", rtn);
              //  List<FunctionInfo> permissionList = funcServ.getPermissionFuncList(userId);
               List<FunctionInfo> permissionList =new ArrayList<FunctionInfo>();
               permissionList.add(parent1);
               permissionList.add(parent2);
               map.put("permissionList", permissionList);
            }
            System.out.println("name="+name+",password="+password+",map="+map);
        }
        catch(Exception e)
        {
            map.put("message", e.getMessage());
        }
        return map;
    }   

     //@RequestMapping("/menulist")
     @CrossOrigin
     @GetMapping(value = "/menulist")
     @ResponseBody
    public Map<String,List<FunctionInfo>> menulist(HttpServletRequest req)
    {
        Map<String,List<FunctionInfo>> map = new HashMap<String,List<FunctionInfo>>();
        /*   FunctionInfo sysFi = new FunctionInfo("sys","系统管理","",1,true,"",null,"1");
        FunctionInfo userFi = new FunctionInfo("user","用户管理","/gf/home",1,true,"/gf/home",null,"sys");
        sysFi.getChild().add(userFi);
        FunctionInfo prdtFi = new FunctionInfo("prdt","产品管理","",1,true,"",null,"1");
        FunctionInfo addFi = new FunctionInfo("add","产品新建","/gf/prdtlist",1,true,"/gf/prdtlist",null,"prdt");
        prdtFi.getChild().add(addFi);*/
        
        
        List<FunctionInfo> child1=new ArrayList<FunctionInfo>();
        
        FunctionInfo f=new FunctionInfo();
        FunctionInfo  parent1=new FunctionInfo();
        f.setName("用户管理");
        f.setVisible(true);
        f.setPath("/gf/home");
        f.setUrl("/gf/home");
        child1.add(f);
        parent1.setName("系统管理");
        parent1.setUrl("");
        parent1.setChild(child1);
        
        FunctionInfo  parent2=new FunctionInfo();
        List<FunctionInfo> child2=new ArrayList<FunctionInfo>();
        FunctionInfo f2=new FunctionInfo();
        f2.setName("产品新建");
        f2.setVisible(true);
        f2.setPath("/gf/prdtlist");
        f2.setUrl("/gf/prdtlist");
        child2.add(f2);
        parent2.setName("产品管理");
        parent2.setUrl("");
        parent2.setChild(child2);
     
     
        List<FunctionInfo> navList = new ArrayList<FunctionInfo>();
        navList.add(parent1);
        navList.add(parent2);
       List<FunctionInfo> permissionList =new ArrayList<FunctionInfo>();
       permissionList.add(parent1);
       permissionList.add(parent2);
        
      
        
    
        
        map.put("navList", navList);
        map.put("permissionList", permissionList);
        return map;
    }
   
}
