package diamond.consoles.modules.console.dto;

import java.util.List;

public record AtualizarConsoleDTO(
    String nome,
    List<Long> codigoJogos
) {

}