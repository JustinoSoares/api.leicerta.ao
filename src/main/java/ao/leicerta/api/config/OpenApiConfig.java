package ao.leicerta.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI leicertaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lei Certa AO — API Jurídica")
                        .description("API moderna que fornece acesso inteligente à legislação angolana. " +
                                "Permite consultar, interpretar e utilizar informações legais de forma simples, " +
                                "rápida e contextualizada.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Lei Certa AO")
                                .url("https://leicerta.ao"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
