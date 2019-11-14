package com.parkson.security;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    //private UserAuthInfo userInfo;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
        //this.userInfo = (UserAuthInfo) userInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

//	public UserAuthInfo getUserInfo() {
//		return userInfo;
//	}
//
//	public void setUserInfo(UserAuthInfo userInfo) {
//		this.userInfo = userInfo;
//	}
//    
    
}
