package edu.hanoi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    HNUserAuthProvider hnUserAuthProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("nguoidung")
                .password("{noop}123")
                .roles("USER")
                .and()
                .withUser("quantri")
                .password("{noop}123456")
                .roles("ADMIN","USER");

//        auth.authenticationProvider(hnUserAuthProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("USER").anyRequest()
                .authenticated().and().httpBasic();


    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        AuthenticationProvider provider = new HNUserAuthProvider();
        return provider;
    }

}
