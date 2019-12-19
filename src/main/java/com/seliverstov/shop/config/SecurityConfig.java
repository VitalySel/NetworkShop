package com.seliverstov.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new Cypher();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select Username username,Password password,enabled from networkstore.user where Username=?")
                .authoritiesByUsernameQuery("select u.username, r.role_name from networkstore.role r JOIN user u ON u.idUser=r.user_idUser where u.username=?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home","/register").permitAll().antMatchers("/admin","/makeadmin","/user").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .and().logout()
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/images/**");
    }
}
