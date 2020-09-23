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

@Configuration//Springi configure ederken kullanýlan bir dosya
@EnableWebSecurity//Bu bir configurasyon dosyasý,onun üzerinden securityi yönetiyorum
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired//Diðer taraftaki beani buraya injecte etmiþ oldum.O tipteki bir nesneyi(beani)bul buraya geç.Bu beande securityDemoApp de.
	private DataSource loginDS;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users=User.withDefaultPasswordEncoder();	
		
		/*//AuthenticationManager configure edecek auth'u böyle editliyorum
		auth.inMemoryAuthentication()
		.withUser(users.username("esra").password("esra").roles("ADMIN"))
		.withUser(users.username("behiye").password("baser").roles("PERSONEL"))
		.withUser(users.username("aysegul").password("berna").roles("PERSONEL"));
		
		//super.configure(auth);*/
		
		//Üst kýsma ihtiyacým kalmadý.Diðer taraftan alýyorum.
		auth.jdbcAuthentication().dataSource(loginDS);//Beani doðrudan referans alýp buraya pas ettim.
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		/*http.authorizeRequests().anyRequest().authenticated();*/
			
		//benim admin sayfasýndaki tüm varyasyonlarýn admin rolü olacak
		http.authorizeRequests()
		.antMatchers("/").authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/personel/**").hasAnyRole("PERSONEL","ADMIN");
		//.antMatchers("/personel/**").hasRole("ADMIN");
		
		
		//Bütün formlarý buraya yönlendirdim
		http.formLogin().loginPage("/loginForm")
		.loginProcessingUrl("/authenticateTheUser").permitAll();
	
	   http.logout().permitAll();
	   
	   http.exceptionHandling().accessDeniedPage("/access-denied");
	}
	

}
