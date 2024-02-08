package com.weCare.security;

//@Configuration
//public class SecurityConfiguration {
//
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.cors(cors -> {
//					cors.configurationSource(new CorsConfigurationSource() {
//						@Override
//						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//							CorsConfiguration cfg = new CorsConfiguration();
//							cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
//							cfg.setAllowedMethods(Collections.singletonList("*"));
//							cfg.setAllowCredentials(true);
//							cfg.setAllowedHeaders(Collections.singletonList("*"));
//							cfg.setExposedHeaders(Arrays.asList("Authorization"));
//							return cfg;
//						}
//					});
//				}).authorizeHttpRequests(auth -> {
//					auth
//					.requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
//					.requestMatchers("/profiles/signIn").permitAll()
//					.requestMatchers(HttpMethod.POST ,"/patients").permitAll()
//					.anyRequest()
//					.authenticated();
//				}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
//
//		return httpSecurity.build();
//	}
//	@Bean
//    PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}
