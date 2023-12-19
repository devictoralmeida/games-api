package diamond.games.modules.console.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.games.modules.console.entity.Console;
import diamond.games.modules.jogo.dto.RetornoJogoParcialDTO;

public record RespostaConsoleCompletoDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String empresa,
        List<RetornoJogoParcialDTO> jogos) {
    public RespostaConsoleCompletoDTO(Console console, List<RetornoJogoParcialDTO> games) {
        this(console.getCodigo(), console.getNome(), console.getDataLancamento(), console.getEmpresa(), games);
    }
}
