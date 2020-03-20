/**  
 * @Title: LoginInterceptor.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-05 07:18:13 
 */ 
 package cn.xiaobai.admin.interceptor;

 import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.xiaobai.admin.entity.User;

import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
  /**
  * @ClassName: LoginInterceptor 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-05 19:18:13
  */
 public class LoginInterceptor  implements HandlerInterceptor{

     @Override
     public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /* HttpSession session = httpServletRequest.getSession();
         String contextPath=session.getServletContext().getContextPath();
         String[] requireAuthPages = new String[]{
                 "index",
         };
        
         String uri = httpServletRequest.getRequestURI();
        
         uri = StringUtils.remove(uri, contextPath+"/");
         String page = uri;
        
         if(begingWith(page, requireAuthPages)){
             User user = (User) session.getAttribute("user");
             if(user==null) {
                 httpServletResponse.sendRedirect("login");
                 return false;
             }
         }
         return true;*/
         
         // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
         if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
             httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
             return true;
         }

         Subject subject = SecurityUtils.getSubject();
         System.out.println(subject.isRemembered());
         System.out.println(subject.isAuthenticated());
         // 使用 shiro 验证
         return subject.isAuthenticated()|| subject.isRemembered();

         
     }

     private boolean begingWith(String page, String[] requiredAuthPages) {
         boolean result = false;
         for (String requiredAuthPage : requiredAuthPages) {
             if(StringUtils.startsWith(page, requiredAuthPage)) {
                 result = true;
                 break;
             }
         }
         return result;
     }
 }