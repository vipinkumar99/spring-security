package com.demo.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.demo.api.auth.ApplicationUserService;
import com.demo.api.enums.ApplicationUserPermission;
import com.demo.api.enums.ApplicationUserRole;
import com.demo.api.jwt.JwtTokenFilter;
import com.demo.api.jwt.JwtUsernamePasswordFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ApplicationUserService applicationUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new JwtUsernamePasswordFilter(authenticationManager()))
				.addFilterAfter(new JwtTokenFilter(), JwtUsernamePasswordFilter.class).authorizeRequests()
				.antMatchers("/", "index").permitAll().antMatchers("/api/**")
				.hasRole(ApplicationUserRole.STUDENT.name())
//		.antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMIN_TRAINEE.name())
				.anyRequest().authenticated().and().httpBasic();
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails student = User.builder().username("vipin").password(encoder.encode("123456"))
//				//.roles(ApplicationUserRole.STUDENT.name()).
//				.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities()).build();
//				
//		UserDetails admin = User.builder().username("vipin123").password(encoder.encode("123456"))
//				//.roles(ApplicationUserRole.ADMIN.name())
//				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//				.build();
//		UserDetails trainee = User.builder().username("nitin123").password(encoder.encode("123456"))
//				//.roles(ApplicationUserRole.ADMIN_TRAINEE.name())
//				.authorities(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
//				.build();
//		return new InMemoryUserDetailsManager(student, admin, trainee);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

}
