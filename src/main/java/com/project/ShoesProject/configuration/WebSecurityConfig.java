package com.project.ShoesProject.configuration;

import com.project.ShoesProject.security.JwtTokenFilter;
import com.project.ShoesProject.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
import org.springframework.security.config.Customizer;
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
<<<<<<< HEAD
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13

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
<<<<<<< HEAD
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
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
<<<<<<< HEAD
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(List.of("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
