package com.sda.spring.projekt1;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_LOGIN = "greg-witczak";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .cors().disable()
                .oauth2Login(
                        httpSecurityOAuth2LoginConfigurer ->
                                httpSecurityOAuth2LoginConfigurer.userInfoEndpoint(
                                        userInfoEndpointConfig ->
                                                userInfoEndpointConfig.userAuthoritiesMapper(userAuthoritiesMapper())
                                )
                );
    }

    private GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return authorities -> {
            final Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities);
            for (GrantedAuthority authority : authorities) {
                if (authority instanceof OAuth2UserAuthority) {
                    final Map<String, Object> attributes = ((OAuth2UserAuthority) authority).getAttributes();
                    final String userLogin = String.valueOf(attributes.get("login"));
                    if (userLogin.equals(ADMIN_LOGIN)) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
                    }
                }
            }
            return grantedAuthorities;
        };
    }

}