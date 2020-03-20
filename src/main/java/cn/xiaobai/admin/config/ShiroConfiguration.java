package cn.xiaobai.admin.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.xiaobai.admin.filter.URLPathMatchingFilter;
import cn.xiaobai.admin.realm.WJRealm;

/**
 * 
 * @ClassName: ShiroConfiguration
 * @Description: TODO
 * @version 1.0
 * @author xiaobaibhs
 * @date 2020-03-09 12:40:03
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getWJRealm());
        return securityManager;
    }

    @Bean
    public WJRealm getWJRealm() {
        WJRealm wjRealm = new WJRealm();
        wjRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return wjRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
            new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 
     * @MethodName: getURLPathMatchingFilter
     * @Description: 在配置类 ShiroConfiguration 增加获取过滤器的方法，注意这里不能使用 @Bean
     * @author xiaobaibhs
     * @return URLPathMatchingFilter
     * @date 2020-03-11 09:41:19
     */
    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/nowhere");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        Map<String, Filter> customizedFilter = new HashMap<>(); // 自定义过滤器设置 1

        customizedFilter.put("url", getURLPathMatchingFilter()); // 自定义过滤器设置 2，命名，需在设置过滤路径前

        // filterChainDefinitionMap.put("/api/authentication", "authc"); // 防鸡贼登录，暂时不需要

        filterChainDefinitionMap.put("/api/menu", "authc");
        filterChainDefinitionMap.put("/api/admin/**", "authc");

        filterChainDefinitionMap.put("/api/admin/**", "url"); // 自定义过滤器设置 3，设置过滤路径

         shiroFilterFactoryBean.setFilters(customizedFilter); // 自定义过滤器设置 4，启用
        // 测试用 注释掉shiroFilterFactoryBean.setFilters(customizedFilter);
        // 为了验证效果，我们注释掉之前的 shiro 配置中关于自定义拦截器的部分。然后用 authc 过滤器保护后台所有接口，也就是说需要先登录再判断是否存在权限
        //filterChainDefinitionMap.put("/api/admin/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 1.这个 authc 即 autentication，是 shiro 自带的过滤器。常用的还有 anon（可匿名访问）、roles（需要角色）、perms（需要权限）等。
        // 2.为啥我们不直接用 perms 呢？
        // 其实使用 perms 才是 Shiro 的祖传解决方案，但是为了配合它的实现，
        // 我们需要在配置文件中添加规则如filterChainDefinitionMap.put("/api/authentication", "perms[/api/admin/user]")，
        // 或者在接口处编写注解如 @RequirePermission("/api/admin/user") ，这样如果我们想要删除或新增权限，
        // 除了修改数据库外还需要重新编写源码，这就比较蓝瘦了。
        // 3.此外，自带过滤器会拦截 options 请求，所以在前后端分离的项目里使用自定义过滤器反而简便一些。。。
        // 4.不过，其实对很多项目来说不太需要动态增删权限，只需要对现有权限进行分配就够了，所以在不分离的情况下使用自带过滤器当然更好。
        return shiroFilterFactoryBean;

    }

}
