package sample.ui.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
public class InfoConfig {

    @Autowired
    private ConfigurableEnvironment environment;

    @Value("${spring.git.properties:classpath:git.properties}")
    private Resource gitProperties;

    @Bean
    public InfoEndpoint infoEndpoint() throws Exception {
        LinkedHashMap<String, Object> info = new LinkedHashMap<String, Object>();
        fillGitProperties(info);
        fillInfoProperties(info);
        return new InfoEndpoint(info);
    }

    private void fillInfoProperties(LinkedHashMap<String, Object> info) throws Exception {
        PropertiesConfigurationFactory<Map<String, Object>> infoFactory = new PropertiesConfigurationFactory<Map<String, Object>>(
            new LinkedHashMap<String, Object>());
        infoFactory.setTargetName("info");
        infoFactory.setPropertySources(environment.getPropertySources());
        info.putAll(infoFactory.getObject());
    }

    private void fillGitProperties(LinkedHashMap<String, Object> info) throws Exception {
        if (gitProperties.exists()) {
            Properties properties = PropertiesLoaderUtils.loadProperties(gitProperties);
            PropertiesConfigurationFactory<Map<String, Object>> gitFactory = new PropertiesConfigurationFactory<Map<String, Object>>(
                    new LinkedHashMap<String, Object>());
            gitFactory.setProperties(properties);
            info.putAll(gitFactory.getObject());
        }
    }
}
