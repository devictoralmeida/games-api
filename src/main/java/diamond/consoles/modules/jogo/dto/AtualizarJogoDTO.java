package diamond.consoles.modules.jogo.dto;

import java.util.List;

public record AtualizarJogoDTO(
    String nome,
    String descricao,
    String website,
    String genero,
    String urlCapa,
    List<Long> consoles
) {
    
}
