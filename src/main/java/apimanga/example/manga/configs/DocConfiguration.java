package apimanga.example.manga.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Manga API")
                        .description("API de um crud de mangas")
                        .contact(new Contact()
                                .name("Ana Luiza Rodrigues Chiamenti")
                                .email("analuizarodrigueschiamenti@gmail.com")));
    }
}
