package diamond.games.modules.jogo.dto;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarJogoDTO(
    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 3, max = 100, message = "Esse campo deve conter de 3 a 100 caracteres")
    String nome,

    String descricao,

    @URL(regexp = "^(http|https|ftp)?://.*$", message = "Insira uma URL válida")
    String website,

    @NotNull Map<String, Long> desenvolvedor,

    @NotBlank(message = "Esse campo não deve estar vazio")
    @Length(min = 3, max = 50, message = "Esse campo deve conter de 3 a 50 caracteres")
    String genero,

    @URL(regexp = "^(http|https|ftp)?://.*$", message = "Insira uma URL válida")
    String urlCapa,

    @NotNull
    List<Map<String, Long>> consoles
) {
    
}
