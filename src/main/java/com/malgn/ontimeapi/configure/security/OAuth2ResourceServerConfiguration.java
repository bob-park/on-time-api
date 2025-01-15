package com.malgn.ontimeapi.configure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.malgn.ontimeapi.configure.security.handler.RestAccessDeniedHandler;
import com.malgn.ontimeapi.configure.security.handler.RestAuthenticationEntryPoint;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class OAuth2ResourceServerConfiguration {

    private final ObjectMapper om;

    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain resourceSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
            requests ->
                requests.anyRequest().authenticated());

        http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        http.exceptionHandling(
            exceptionHandler ->
                exceptionHandler.authenticationEntryPoint(authenticationEntryPoint())
                    .accessDeniedHandler(accessDeniedHandler()));

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource));

        return http.build();
    }

    /*
     * exception handling
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint(om);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler(om);
    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return JwtDecoders.fromOidcIssuerLocation(properties.getJwt().getIssuerUri());
    }

}
