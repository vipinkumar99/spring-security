package com.demo.api.auth;

import java.util.Optional;

public interface ApplicationUserDao {
Optional<ApplicationUser>getByUsername(String username);
}
