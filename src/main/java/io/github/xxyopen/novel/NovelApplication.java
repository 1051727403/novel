package io.github.xxyopen.novel;

import io.github.xxyopen.novel.core.constant.SystemConfigConsts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@OpenAPIDefinition(info = @Info(title = "novel 项目接口文档", version = "v3.2.0", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")))
@SecurityScheme(type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, name = SystemConfigConsts.HTTP_AUTH_HEADER_NAME, description = "登录 token")
@SpringBootApplication
@MapperScan("io.github.xxyopen.novel.dao.mapper")
@EnableCaching
@EnableScheduling
@Slf4j
public class NovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            Map<String, CacheManager> beans = context.getBeansOfType(CacheManager.class);
            log.info("加载了如下缓存管理器：");
            beans.forEach((k, v) -> {
                log.info("{}:{}", k, v.getClass().getName());
                log.info("缓存：{}", v.getCacheNames());
            });

        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .requestMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeRequests(requests -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
        http.httpBasic();
        return http.build();
    }

}
