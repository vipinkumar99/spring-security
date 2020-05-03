package org.oauth.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("krish").password(encoder.encode("krish@123"))
				.roles("ADMIN", "USER", "MANAGER").authorities("CAN_READ", "CAN_WRITE", "CAN_DELETE").and()
				.withUser("vipin").password(encoder.encode("vipin@123")).roles("USER")
				.authorities("CAN_READ", "CAN_WRITE");
	}

}
