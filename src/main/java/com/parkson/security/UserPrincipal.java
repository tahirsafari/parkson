package com.parkson.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parkson.model.User;

public class UserPrincipal implements UserDetails {
    private Long userId;
    private String username;
    @JsonIgnore
    private String password;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;


    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long userId, String username, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }

    public static UserPrincipal createUserAuthInfo(User  user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
    	//user.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName().name())
//        ).collect(Collectors.toList());

        return new UserPrincipal(
                Long.valueOf(user.getId()),
                user.getUsername(),
                grantedAuths
        );
    }
    


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



    public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }

}