package com.learnhub.config;

import com.learnhub.user.infrastructure.rest.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/cursos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/cursos/*/resenas/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api-docs/**", "/swagger-ui/**").permitAll()

                // Courses - admin write
                .requestMatchers(HttpMethod.POST, "/api/cursos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/cursos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/cursos/**").hasRole("ADMIN")

                // Categories - admin write
                .requestMatchers(HttpMethod.POST, "/api/categorias/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/categorias/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/categorias/**").hasRole("ADMIN")

                // Users - admin only
                .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/*/estado").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/usuarios/*/pedidos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/*/soft-delete").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/*/restore").hasRole("ADMIN")

                // Orders - admin read all / write delete
                .requestMatchers(HttpMethod.GET, "/api/pedidos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/pedidos/*/soft-delete").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/pedidos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/pedidos/*/restore").hasRole("ADMIN")

                // Reviews - admin write delete
                .requestMatchers(HttpMethod.PUT, "/api/resenas/*/soft-delete").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/resenas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/resenas/*/restore").hasRole("ADMIN")

                // Purchases - admin only
                .requestMatchers(HttpMethod.POST, "/api/compras/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/compras/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/compras/*/soft-delete").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/compras/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/compras/*/restore").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private CorsConfigurationSource corsSource() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
