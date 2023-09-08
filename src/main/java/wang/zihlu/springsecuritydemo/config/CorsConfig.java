package wang.zihlu.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import wang.zihlu.springsecuritydemo.config.properties.CorsProperties;
import wang.zihlu.springsecuritydemo.constant.HttpMethod;

import java.util.Arrays;
import java.util.Optional;

/**
 * CorsConfig
 *
 * @author Zihlu Wang
 * @since 04 Sept, 2023
 */
@Configuration
@EnableConfigurationProperties({CorsProperties.class})
public class CorsConfig {

    private final CorsProperties corsProperties;

    @Autowired
    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.stream(corsProperties.getAllowedOrigins()).toList());
        configuration.setAllowedMethods(Arrays.stream(corsProperties.getAllowedMethods()).map(HttpMethod::name).toList());
        configuration.setAllowedHeaders(Arrays.stream(corsProperties.getAllowedHeaders()).toList());
        configuration.setExposedHeaders(Arrays.stream(corsProperties.getExposedHeaders()).toList());

        Optional.of(corsProperties)
                .map(CorsProperties::getAllowCredentials)
                .ifPresent(configuration::setAllowCredentials);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
