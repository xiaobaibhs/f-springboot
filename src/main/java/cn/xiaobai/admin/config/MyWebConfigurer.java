/**
 * @Title: MyWebConfigurer.java
 * @Description: TODO
 * @author xiaobaibhs
 * @date 2020-03-05 07:22:08
 */
package cn.xiaobai.admin.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

import cn.xiaobai.admin.interceptor.LoginInterceptor;

/**
 * @ClassName: MyWebConfigurer
 * @Description: TODO
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-05 19:22:08
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    /* @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }*/
    /*1.之前我们在拦截器 LoginInterceptor 中配置的路径，即 index，触发的时机是在拦截器生效之后。
     * 也就是说，我们访问一个 URL，会首先通过 Configurer 判断是否需要拦截，如果需要，才会触发拦截器 LoginInterceptor，
     * 根据我们自定义的规则进行再次判断。
      /index 与 /index.html 是不同的，也就是说 /index 会触发拦截器而 /index.html 不会，
       * 但根据拦截器 LoginInterceptor 中我们定义的判断条件，以 /index 开头的路径都会被转发，包括 index.html。
    因为我们做的是单页面应用，之前通过配置 ErrorPage，实际上访问所有路径都会重定向到 /index.html 。
    我们直接在浏览器地址栏输入 /index 会触发拦截器，经过拦截器重定向到 /login，然后 /login 再经过 Configurer 的判断，
    再次触发拦截器，由于不在需要拦截的路径中，所以被放行，页面则重新定向到了 /index.html，
    如果没有再 Configurer 中取消对 /index.html 的拦截，则会再次触发拦截器，再次重定向到 /login，引发错误。
    上述过程比较绕，这是我开发时的失误，但我觉得恰好可以帮助大家理解拦截器与单页面应用，所以保留了下来。
    2.之前我们在做登录拦截的时候使用了拦截器，即 Interceptor。由于 Shiro 的权限机制要靠它自身提供的过滤器实现，所以我们现在弃用之前的拦截器。
    首先在 MyWebConfigurer 中删除拦截器配置
     * */
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor(getLoginInterceptor())
    // .addPathPatterns("/**")
    // .excludePathPatterns("/index.html")
    // .excludePathPatterns("/api/register")
    // .excludePathPatterns("/api/login")
    // .excludePathPatterns("/api/logout")
    // .excludePathPatterns("/api/books");
    // }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 请求允许跨域
        // registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
        // 所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        // registry.addMapping("/**").allowCredentials(true).allowedOrigins("http://localhost:8080")
        // .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").allowedHeaders("*") .maxAge(3600);
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
            .allowCredentials(true).allowedHeaders("*").maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "e:/workspace/img/");
    }
}
