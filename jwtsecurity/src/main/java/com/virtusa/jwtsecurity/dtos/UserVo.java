package com.virtusa.jwtsecurity.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

	private String userName;
	private String userpwd;
	private Set<String> roles;
	

}
