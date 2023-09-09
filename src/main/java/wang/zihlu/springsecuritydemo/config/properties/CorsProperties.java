package wang.zihlu.springsecuritydemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMethod;

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

    private RequestMethod[] allowedMethods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE};

    private String[] allowedHeaders = {"Content-Type", "Authorization"};

    private String[] exposedHeaders = {"Content-Type", "Authorization"};

    private Boolean allowCredentials;

}
