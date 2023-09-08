package wang.zihlu.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
            requestCustomizer // Configurations about authentication
                    .requestMatchers("/login").permitAll() // - requestMatchers: The listed request uri patterns that
                                                             // have been managed by Spring Security
                                                             // - permitAll: This method allows anonymous requests and
                                                             // grant all people accessing this uri
                    .anyRequest().authenticated() // - anyRequest: This specifies all the request uris that is not
                                                  // listed above.
                                                  // - authenticated: This method requires user is authenticated(no
                                                  // authorization required)
            ;
        }).formLogin((formLoginConfigurer) -> {
            formLoginConfigurer
                    .loginPage("/login").permitAll() // Specify the login page and permit all users to access
                                                     // without any authentication
                    .loginProcessingUrl("/login") // Specify the login API

            ;
        }).csrf(AbstractHttpConfigurer::disable).cors((corsConfigurer) -> {
            corsConfigurer.configurationSource(corsConfigurationSource);
        }).logout((logoutConfigurer) -> {
            logoutConfigurer.invalidateHttpSession(true);
        }).build();
    }

}
