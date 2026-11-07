/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 5 | Class: SecurityConfig
 */
package chnu.edu.websecurity26.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/api/v1/tracks/hello/admin").hasRole("ADMIN")
                        .requestMatchers("/api/v1/tracks/hello/user").hasRole("USER")
                        .requestMatchers("/api/v1/tracks/hello/member").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/v1/tracks/hello/superadmin").hasRole("SUPERADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("user")
                        .password(passwordEncoder.encode("user"))
                        .roles("USER")
                        .build(),
                User.withUsername("superadmin")
                        .password(passwordEncoder.encode("superadmin"))
                        .roles("SUPERADMIN")
                        .build());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
