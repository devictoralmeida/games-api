package diamond.games.modules.console.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CriarConsoleDTO(
    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 3, max = 100, message = "Esse campo deve conter de 3 a 100 caracteres")
    String nome,

    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 3, max = 100, message = "Esse campo deve conter de 3 a 100 caracteres")
    String empresa
) {
    
}
