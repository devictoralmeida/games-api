package diamond.games.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Games API")
                        .description(
                                "API Rest desenvolvida para a trilha de Java, da Empresa Diamond Solutions, contendo as funcionalidades de CRUD de desenvolvedores, consoles e jogos.")
                        .contact(new Contact()
                                .name("Victor Almeida")
                                .email("victoremmanuelmn@gmail.com")));
    }
}
