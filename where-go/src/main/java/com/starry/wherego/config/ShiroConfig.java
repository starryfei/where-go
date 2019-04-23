package com.starry.wherego.config;

import com.starry.wherego.util.ShiroKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-22 22:11
 **/
@Configuration
@Slf4j
public class ShiroConfig  {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        // anon 无需认证
        // authc 需要认证
        // user remberme
        // prems 得到资源权限才可以访问
        // role 资源得到角色权限才可以访问

        //过滤 swagger
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");

        // controller
        filterMap.put("/common/**","anon");
        filterMap.put("/map/**","authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //用户未登录不进行跳转，而是自定义返回json数据
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new ShiroLoginFilter()) ;
        filterFactoryBean.setFilters(filters);

        return filterFactoryBean;
    }

    @Bean()
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(getUserRelam());
        return securityManager;
    }

    @Bean
    public UserRelam getUserRelam(){
        UserRelam relam = new UserRelam();
        relam.setCredentialsMatcher(credentialsMatcher());
        return relam;
    }


    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        log.info("密码校验..");
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
