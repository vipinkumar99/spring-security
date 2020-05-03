package com.demo.api.enums;

import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserPermission {
	STUDENT_READ("student:read"), STUDENT_WRITE("student:write"), COURSE_READ("course:read"),
	COURSE_WRITE("course:write");

	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	public static Set<ApplicationUserPermission> getAllPermissions(){
		Set<ApplicationUserPermission>per=new HashSet<>();
		per.add(STUDENT_READ);
		per.add(STUDENT_WRITE);
		per.add(COURSE_READ);
		per.add(COURSE_WRITE);
		return per;
		
	}
	
	public static Set<ApplicationUserPermission> getReadPermissions(){
		Set<ApplicationUserPermission>per=new HashSet<>();
		per.add(STUDENT_READ);
		per.add(COURSE_READ);
		return per;		
	}

}
