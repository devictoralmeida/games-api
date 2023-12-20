package diamond.games.modules.desenvolvedor.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record CriarDesenvolvedorDTO(
    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 3, max = 100, message = "Esse campo deve conter de 3 a 100 caracteres")
    String nome,

    @URL(regexp = "^(http|https|ftp)?://.*$", message = "Insira uma URL válida")
    String website,

    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 2, max = 50, message = "Esse campo deve conter de 2 a 50 caracteres")
    String sede
) {
    
}
