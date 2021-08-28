package top.zhangmingke.springfoxswaggerspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("张明珂", "http://www.cnblogs.com/getupmorning/", "zhaoming0018@126.com");
        return new ApiInfoBuilder()
                .title("湖州揭榜挂帅")
                .description("接口文档")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}
