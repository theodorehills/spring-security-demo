package wang.zihlu.springsecuritydemo.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * SecurityConfig
 *
 * @author Zihlu Wang
 * @since 01 Sept, 2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    @Autowired
    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests((requestCustomizer) -> {
            requestCustomizer
                    .requestMatchers("/admin").hasRole("admin")
                    .requestMatchers("/user").hasAnyRole("admin", "user")
                    .requestMatchers("/app").permitAll()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated();
        }).formLogin((formLoginConfigurer) -> {
            formLoginConfigurer
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/login");
        }).exceptionHandling((exceptionHandlingConfigurer) -> {
            exceptionHandlingConfigurer.accessDeniedPage("/unauthorised");
        }).csrf(AbstractHttpConfigurer::disable).cors((corsConfigurer) -> {
            corsConfigurer.configurationSource(corsConfigurationSource);
        }).logout((logoutConfigurer) -> {
            logoutConfigurer.invalidateHttpSession(true);
        }).build();
    }

    @Bean
    @SuppressWarnings("deprecation") // This password encoder will be used for dev environment.
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("123456")
                        .roles("admin", "user")
                        .build(),
                User.withUsername("user")
                        .password("123456")
                        .roles("user")
                        .build());
    }

}
