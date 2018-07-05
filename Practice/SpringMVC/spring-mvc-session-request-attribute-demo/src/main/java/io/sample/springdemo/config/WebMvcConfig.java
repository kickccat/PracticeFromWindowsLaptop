package io.sample.springdemo.config;

import io.sample.springdemo.interceptors.ExecutionTimerInterceptor;
import io.sample.springdemo.interceptors.HeaderInterceptor;
import io.sample.springdemo.interceptors.VisitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("io.sample.springdemo")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private HeaderInterceptor headerInterceptor;
    
    @Autowired
    private VisitorInterceptor visitorInterceptor;
    
	@Autowired
	private ExecutionTimerInterceptor executionTimerInterceptor;
	
	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		return dsLookup.getDataSource("jdbc/springdb");
	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("testMvcHome");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(headerInterceptor);
        registry.addInterceptor(visitorInterceptor);
		registry.addInterceptor(executionTimerInterceptor).addPathPatterns("/location");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/css/test/");
	}
}
