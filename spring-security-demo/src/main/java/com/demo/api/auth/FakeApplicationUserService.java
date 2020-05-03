package com.demo.api.auth;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.demo.api.enums.ApplicationUserRole;

@Repository("Fake")
public class FakeApplicationUserService implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Optional<ApplicationUser> getByUsername(String username) {
		return getApplicationUser().stream().filter(r -> r.getUsername().equals(username)).findFirst();
	}

	private List<ApplicationUser> getApplicationUser() {
		return Arrays.asList(
				new ApplicationUser("vipin", encoder.encode("123456"),
						ApplicationUserRole.STUDENT.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("vipin123", encoder.encode("123456"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("nitin123", encoder.encode("123456"),
						ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(), true, true, true, true));
	}

}
