package diamond.games.modules.console.dto;

import java.util.List;

public record AtualizarConsoleDTO(
    String nome,
    List<Long> jogos
) {

}
