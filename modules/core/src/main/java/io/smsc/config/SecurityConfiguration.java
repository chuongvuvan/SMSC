package io.smsc.config;

import io.smsc.jwt.JWTAuthenticationEntryPoint;
import io.smsc.jwt.JWTAuthenticationTokenFilter;
import io.smsc.jwt.service.JWTTokenGenerationService;
import io.smsc.jwt.service.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionCacheOptimizer;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The SecurityConfiguration class is used for configuring Spring
 * Security service considering JWT {@link JWTAuthenticationTokenFilter}
 * implementation.
 *
 * @author Nazar Lipkovskyy
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@Import(RepositoryIdExposingConfiguration.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTUserDetailsService userDetailsService;
    private final JWTAuthenticationEntryPoint unauthorizedHandler;
    private final JWTTokenGenerationService tokenGenerationService;

    @Autowired
    public SecurityConfiguration(
            JWTUserDetailsService userDetailsService,
            JWTAuthenticationEntryPoint unauthorizedHandler,
            JWTTokenGenerationService tokenGenerationService
    ) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.tokenGenerationService = tokenGenerationService;
    }

   @Autowired
   private ApplicationContext context;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService);
    }

    /**
     * Gets the {@link JWTAuthenticationTokenFilter} bean
     *
     * @return authenticationTokenFilter
     */
    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JWTAuthenticationTokenFilter(userDetailsService, tokenGenerationService);
    }

//    @Bean
//    public DefaultMethodSecurityExpressionHandler expressionHandler() {
//        DefaultMethodSecurityExpressionHandler securityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
//        securityExpressionHandler.setPermissionEvaluator(permissionEvaluator());
//        securityExpressionHandler.setPermissionCacheOptimizer(permissionCacheOptimizer());
//        return new DefaultMethodSecurityExpressionHandler();
//    }
//
//    @Bean
//    public AclPermissionCacheOptimizer permissionCacheOptimizer() {
//        return new AclPermissionCacheOptimizer(aclService());
//    }
//
//    @Bean
//    public AclPermissionEvaluator permissionEvaluator() {
//        return new AclPermissionEvaluator(aclService());
//    }
//
//    @Bean
//    public JdbcMutableAclService aclService() {
//        DataSource dataSource =(DataSource) context.getBean("dataSource");
//        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
//    }
//
//    @Bean
//    public BasicLookupStrategy lookupStrategy() {
//        return new BasicLookupStrategy((DataSource)context.getBean("dataSource"), aclCache(), aclAuthorizationStrategy(), auditLogger());
//    }
//
//    @Bean
//    public ConsoleAuditLogger auditLogger() {
//        return new ConsoleAuditLogger();
//    }
//
//    @Bean
//    public AclAuthorizationStrategyImpl aclAuthorizationStrategy() {
//        return new AclAuthorizationStrategyImpl(grantedAuthority(), grantedAuthority(), grantedAuthority());
//    }
//
//    @Bean
//    public SimpleGrantedAuthority grantedAuthority() {
//        return new SimpleGrantedAuthority("ROLE_ADMIN_POWER_USER");
////      return new SimpleGrantedAuthority("ROLE_ADMINISTRATOR");
//    }
//
//    @Bean
//    public EhCacheBasedAclCache aclCache() {
//        EhCacheFactoryBean bean = new EhCacheFactoryBean();
//        bean.setCacheManager(new EhCacheManagerFactoryBean().getObject());
//        bean.setCacheName("aclCache");
//        return new EhCacheBasedAclCache(bean.getObject(), new DefaultPermissionGrantingStrategy(auditLogger()), aclAuthorizationStrategy());
//    }

    /**
     * This is the main method to configure the {@link HttpSecurity}.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // /rest/auth/token is used for token receiving and updating
                .antMatchers("/").permitAll()
                .antMatchers("/rest/repository/browser/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/rest/auth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                // Call our errorHandler if authentication/authorization fails
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http
                .headers()
                .cacheControl().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admin/**");
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
