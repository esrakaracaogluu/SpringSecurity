package com.esra.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration//Springi configure ederken kullan�lan bir dosya
@EnableWebSecurity//Bu bir configurasyon dosyas�,onun �zerinden securityi y�netiyorum
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired//Di�er taraftaki beani buraya injecte etmi� oldum.O tipteki bir nesneyi(beani)bul buraya ge�.Bu beande securityDemoApp de.
	private DataSource loginDS;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users=User.withDefaultPasswordEncoder();	
		
		/*//AuthenticationManager configure edecek auth'u b�yle editliyorum
		auth.inMemoryAuthentication()
		.withUser(users.username("esra").password("esra").roles("ADMIN"))
		.withUser(users.username("behiye").password("baser").roles("PERSONEL"))
		.withUser(users.username("aysegul").password("berna").roles("PERSONEL"));
		
		//super.configure(auth);*/
		
		//�st k�sma ihtiyac�m kalmad�.Di�er taraftan al�yorum.
		auth.jdbcAuthentication().dataSource(loginDS);//Beani do�rudan referans al�p buraya pas ettim.
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		/*http.authorizeRequests().anyRequest().authenticated();*/
			
		//benim admin sayfas�ndaki t�m varyasyonlar�n admin rol� olacak
		http.authorizeRequests()
		.antMatchers("/").authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/personel/**").hasAnyRole("PERSONEL","ADMIN");
		//.antMatchers("/personel/**").hasRole("ADMIN");
		
		
		//B�t�n formlar� buraya y�nlendirdim
		http.formLogin().loginPage("/loginForm")
		.loginProcessingUrl("/authenticateTheUser").permitAll();
	
	   http.logout().permitAll();
	   
	   http.exceptionHandling().accessDeniedPage("/access-denied");
	}
	

}
