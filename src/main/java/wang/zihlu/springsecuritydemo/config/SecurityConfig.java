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
            // 授权相关的配置
            requestCustomizer
                    // requestMatchers: 请求URI
                    // permitAll: 匿名访问，所有人都可以访问
                    .requestMatchers("/login").permitAll()
                    // anyRequest: 所有请求
                    // authenticated: 认证（登录）
                    .anyRequest().authenticated();
        }).formLogin((formLoginConfigurer) -> {
            formLoginConfigurer
                    .loginProcessingUrl("/login") // 登录接口
                    .successHandler((request, response, authentication) -> {
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("登录成功！");
                    })
                    .failureHandler((request, response, exception) -> {
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("登录失败！");
                    })
            ;
        }).csrf(AbstractHttpConfigurer::disable).logout((logoutConfigurer) -> {
            logoutConfigurer.invalidateHttpSession(true);
        }).cors(corsConfigurer -> {
            corsConfigurer.configurationSource(corsConfigurationSource);
        }).build();
    }

}
