package com.example.demo.login.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.login.ServiceImpl.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtUtils jwtUtils;
    private static final String[] PUBLIC_URLS = {
        "/auth/**",
        "/error"
    };
    private static final String[] ADMIN_URLS = {
        "/mantener/**",
        "/asignar/**",
        "/roles/**",
        "/users/**"
    };

    private static final String[] PRACTICANTE_URLS = {
          "/cartapresentacion/**",
        "/tipodocumentacioneprac/**",
        "/tipodocumentacionesPRAC/**",
        "/ppp/**",
    };
    private static final String[] COORDINADOR_URLS = {
        "/tipodocumentaciones/**",
        "/documentos/**",
        "/asignar/**",
        "/users/**",
        "/practicantelogin/**",
        "/lineas/**",
        "/mantenercord/**",
        "/pppcordinador/**",

        
    };
    private static final String[] SECRETARIA_URLS = {
        
        "/asignar/**"
        
    };
    private static final String[] DIRECTOR_URLS = {
        
        "/asignar/**"
        
    };
    private static final String[] TUTOR_URLS = {
        
        "/asignar/**"
        
    };

    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "X-Requested-With",
            "Accept",
            "Origin"
        ));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->  
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                   

                    http.requestMatchers(PUBLIC_URLS).permitAll()
                    .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
                    .requestMatchers(PRACTICANTE_URLS).hasRole("PRACTICANTE")
                    .requestMatchers(COORDINADOR_URLS).hasRole("COORDINADOR")
                    .requestMatchers(SECRETARIA_URLS).hasRole("SECRETARIA")
                    .requestMatchers(DIRECTOR_URLS).hasRole("DIRECTOR")
                    .requestMatchers(TUTOR_URLS).hasRole("TUTOR")
                        .anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   
}
