package com.esra.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

    @Configuration
	@EnableWebMvc//Spring yetenekleri ile ayaða kaldýrma?
	@ComponentScan(basePackages="com.esra")
    @PropertySource("classpath:db.properties")//Senin için uygun olan deðiþkenleri burda al dedik.Bunuda enver.deðiþkeninde tutuyor.
	public class SecurityDemoAppConfig {
    	
    	@Autowired
    	private Environment env;//Çevre deðiþkeni. tanýmladýk.Bunun üzerinden properties dosyasýndan okumamýz kolay olacak.
    	
    	private Logger logger=Logger.getLogger(getClass().getName());//Nerede çalýþtýðýný bir defa configüre ediyoruz.Daha sonrasýnda istediðimiz zaman çaðrý yaptýðýmýzda configure ediyor
		
    	@Bean
    	public DataSource loginDS() {//Datasource tipinde bir bean oluþturdum.
    		ComboPooledDataSource loginDS= new ComboPooledDataSource();
    		
    		logger.info("--> Driver bulunuyor.");
    		
    		try {
				loginDS.setDriverClass(env.getProperty("jdbc.driver"));
				logger.info("--> Driver bulundu.");
			} catch (PropertyVetoException e) {
				logger.info("--> Driver bulunamadý.");
				throw new RuntimeException("Driver not found");
			}
    		
    		logger.info("--> Setting url,user,password");
    		
    		loginDS.setJdbcUrl(env.getProperty("jdbc.url"));
    		loginDS.setUser(env.getProperty("jdbc.user"));
    		loginDS.setPassword(env.getProperty("jdbc.password"));
    		
    		logger.info("--> Setting pool properties");
    		
    		loginDS.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
    		loginDS.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
    		loginDS.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
    		loginDS.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
    		
    		return loginDS;
    	}
    	
		@Bean
		public ViewResolver viewResolver() {
			
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			
			viewResolver.setPrefix("/WEB-INF/view/");
			viewResolver.setSuffix(".jsp");
			
			return viewResolver;
		}

	}


