package com.example.dailyshop.model.account;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String account;

    @Getter
    private boolean checkProfile;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken, Long id, String account, Collection<? extends GrantedAuthority> roles, boolean checkProfile) {
        this.token = accessToken;
        this.account = account;
        this.roles = roles;
        this.id = id;
        this.checkProfile = checkProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setCheckProfile(boolean checkProfile) {
        this.checkProfile = checkProfile;
    }

}
