package com.project.ShoesProject.configuration;

import com.project.ShoesProject.security.JwtTokenFilter;
import com.project.ShoesProject.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers(
                                    String.format("%s/users/register",apiPrefix),
                                    String.format("%s/users/login",apiPrefix),
                                    String.format("%s/users/refreshToken",apiPrefix)
                            )
                                    .permitAll()
                                    //request products
                                    .requestMatchers(HttpMethod.GET,
                                         String.format("%s/products**",apiPrefix)).hasAnyAuthority("ADMIN","USER")
                                    .requestMatchers(HttpMethod.GET,
                                            String.format("%s/products/**",apiPrefix)).hasAnyAuthority("ADMIN","USER")
                                    .requestMatchers(HttpMethod.POST,
                                            String.format("%s/products/**",apiPrefix)).hasAuthority("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,
                                            String.format("%s/products/**",apiPrefix)).hasAuthority("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,
                                            String.format("%s/products/**",apiPrefix)).hasAuthority("ADMIN")
                                    // request categories
                                    .requestMatchers(HttpMethod.GET,
                                            String.format("%s/categories**", apiPrefix)).hasAnyAuthority("USER","ADMIN")
                                    .requestMatchers(HttpMethod.POST,
                                            String.format("%s/categories/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,
                                            String.format("%s/categories/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,
                                            String.format("%s/categories/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    //request orders
                                    .requestMatchers(HttpMethod.POST,
                                            String.format("%s/orders/**", apiPrefix)).hasAnyAuthority("USER")
                                    .requestMatchers(HttpMethod.GET,
                                            String.format("%s/orders/**", apiPrefix)).hasAnyAuthority("ADMIN","USER")
                                    .requestMatchers(HttpMethod.PUT,
                                            String.format("%s/orders/**", apiPrefix)).hasAnyAuthority("USER")
                                    .requestMatchers(HttpMethod.DELETE,
                                            String.format("%s/orders/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    // request order details
                                    .requestMatchers(HttpMethod.POST,
                                            String.format("%s/order_details/**", apiPrefix)).hasAnyAuthority("USER")
                                    .requestMatchers(HttpMethod.GET,
                                            String.format("%s/order_details/**", apiPrefix)).hasAnyAuthority("ADMIN","USER")
                                    .requestMatchers(HttpMethod.PUT,
                                            String.format("%s/order_details/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,
                                            String.format("%s/order_details/**", apiPrefix)).hasAnyAuthority("ADMIN")
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .userDetailsService(userDetailsServiceImpl)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
