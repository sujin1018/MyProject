package com.project.book.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.book.security.jwt.JwtFilter;
import com.project.book.security.jwt.TokenProvider;
import com.project.book.security.jwt.handler.JwtAccessDeniedHandler;
import com.project.book.security.jwt.handler.JwtAuthenticationEntryPoint;
import com.project.book.security.jwt.handler.JwtExceptionFilter;
import com.project.book.security.oauth.handler.OAuthFailureHandler;
import com.project.book.security.oauth.handler.OAuthSuccessHandler;
import com.project.book.security.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final ObjectMapper objectMapper;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final OAuthSuccessHandler oAuthSuccessHandler;
	private final OAuthFailureHandler oAuthFailureHandler;
	private final CustomOAuth2UserService customOAuthUserService;

	private final String[] authWhiteList = {
		// oauth
		"/oauth2/**",
		// oauth test(삭제예정)
		"/test",
		"/success",

		// swagger
		"/swagger-ui/**",
		"/v3/api-docs/**"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 폼 비활성화
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))

			.authorizeHttpRequests(authorize -> { authorize
				.requestMatchers(authWhiteList).permitAll()
				.anyRequest().hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
			})
			.oauth2Login(oauth2 -> { // 소셜로그인
				oauth2
					.successHandler(oAuthSuccessHandler)
					.failureHandler(oAuthFailureHandler)
					.userInfoEndpoint(userInfoEndpointConfig -> {
						userInfoEndpointConfig
							.userService(customOAuthUserService);
					});
			})
			.exceptionHandling(exceptionHandlingConfigurer ->
				exceptionHandlingConfigurer
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.accessDeniedHandler(jwtAccessDeniedHandler)
			)
			.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtExceptionFilter(objectMapper), JwtFilter.class);

		return http.build();
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		//        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
