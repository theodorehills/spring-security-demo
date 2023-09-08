package wang.zihlu.springsecuritydemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import wang.zihlu.springsecuritydemo.constant.HttpMethod;

/**
 * CorsProperties
 *
 * @author Zihlu Wang
 * @since 04 Sept, 2023
 */
@Data
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    private String[] allowedOrigins = {"*"};

    private HttpMethod[] allowedMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

    private String[] allowedHeaders = {"Content-Type", "Authorization"};

    private String[] exposedHeaders = {"Content-Type", "Authorization"};

    private Boolean allowCredentials;

}
