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

    /**
     * Origins that can access this system.
     */
    private String[] allowedOrigins = {"*"};

    /**
     * Allowed request methods.
     */
    private RequestMethod[] allowedMethods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE};

    /**
     * Allowed headers.
     */
    private String[] allowedHeaders = {"Content-Type", "Authorization"};

    /**
     * Headers that will be exposed to the front-end.
     */
    private String[] exposedHeaders = {"Content-Type", "Authorization"};

    private Boolean allowCredentials;

}
