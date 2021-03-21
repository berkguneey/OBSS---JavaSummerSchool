package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private DataSource dataSource;
	
	@Autowired
	public SecSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT user_email, user_password, 1 FROM `user` WHERE user_email=?")
		.authoritiesByUsernameQuery("select user_email, user_role from user WHERE user_email = ?")
		.passwordEncoder(passwordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
		 .authorizeRequests()
		 .antMatchers("/login**").permitAll()
		 .antMatchers("/admin**").hasRole("ADMIN")
		 .antMatchers("/admin/**").hasRole("ADMIN")
		 .anyRequest().authenticated()
		 .and()
		
		 .authorizeRequests()
		 .anyRequest().permitAll()
		 .and()
		
		 .formLogin()
		 .loginPage("/login")
		 .defaultSuccessUrl("/books")
		 .failureUrl("/login?error")
		 .and()
		 .logout().logoutSuccessUrl("/login").and().csrf().disable();
        
    	http.cors().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("**")
        .permitAll().anyRequest().authenticated().and().csrf().disable().sessionManagement();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}