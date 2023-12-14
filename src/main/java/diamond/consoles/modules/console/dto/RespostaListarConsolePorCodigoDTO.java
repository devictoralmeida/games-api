package diamond.consoles.modules.console.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.jogo.dto.RetornoJogoParcialDTO;

public record RespostaListarConsolePorCodigoDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String empresa,
        List<RetornoJogoParcialDTO> jogos) {
    public RespostaListarConsolePorCodigoDTO(Console console, List<RetornoJogoParcialDTO> games) {
        this(console.getCodigo(), console.getNome(), console.getDataLancamento(), console.getEmpresa(), games);
    }
}
