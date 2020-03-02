package hust.shixun.grouptravel.config;



import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfigAdapter extends WebMvcConfigurerAdapter {

    /**
     * 资源映射配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(AppConstant.FILE_MAP)
                .addResourceLocations("file:" + AppConstant.FILE_PATH);

        super.addResourceHandlers(registry);
    }
}

