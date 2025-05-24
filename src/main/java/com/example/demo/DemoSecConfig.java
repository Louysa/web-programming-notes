package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class DemoSecConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService)
            throws Exception {

        httpSecurity.authorizeRequests(auth ->
            auth.requestMatchers(
                    "/css/**",
                    "/img/**",
                    "/actuator/info",
                    "/login.html",
                    "/h2-db-console/**"
                )
                .permitAll()
                .requestMatchers("/user-page.html").hasRole("USER")
                .requestMatchers("/editor-page.html").hasRole("EDITOR")
                .requestMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login.html")
            .loginProcessingUrl("/login")  
            .defaultSuccessUrl("/user-page.html", true)
            .failureUrl("/login.html?loginFailed=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login.html?logout=true")
            .permitAll()
        )
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-db-console/**")
        )
        .headers(headers -> headers
            .frameOptions().sameOrigin()
        );

        httpSecurity.exceptionHandling(exc -> exc.accessDeniedPage("/access-denied.html"));

        // Configure authentication
        httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/h2-console/**");
    }

    @Bean 
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
            "select username, password, enabled from users where username = ?");
        manager.setAuthoritiesByUsernameQuery(
            "select username, authority from authorities where username = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
