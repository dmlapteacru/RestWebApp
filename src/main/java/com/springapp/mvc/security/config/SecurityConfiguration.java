package com.springapp.mvc.security.config;

import com.springapp.mvc.validator.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 1 from users where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from users u inner join users_roles ur on(u.id=ur.user_id) inner join roles r on(ur.roles_id=r.id) where u.username=?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/allusers").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/editUser").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/profile").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/secret").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/adminpanel").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/newuser").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/deleteUser").access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/allusers")
                .failureUrl("/error")
                .usernameParameter("username").passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and().rememberMe().tokenValiditySeconds(1209600).rememberMeParameter("remember-me")
                .and().csrf().disable();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public UserValidation userValidation(){
        return new UserValidation();
    }
}
