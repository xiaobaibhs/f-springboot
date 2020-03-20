/**  
 * @Title: ErrorConfig.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-05 07:03:59 
 */ 
 package cn.xiaobai.admin.error;
 import org.springframework.boot.web.server.ErrorPageRegistrar;
 import org.springframework.boot.web.server.ErrorPage;
 import org.springframework.boot.web.server.ErrorPageRegistry;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Component;

  /**
  * @ClassName: ErrorConfig 
  * @Description: TODO
  * @version 1.0 
  * @author xiaobaibhs
  * @date 2020-03-05 19:03:59
  */
 @Component
 public class ErrorConfig implements ErrorPageRegistrar {

     @Override
     public void registerErrorPages(ErrorPageRegistry registry) {
         ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
         registry.addErrorPages(error404Page);
     }

 }

