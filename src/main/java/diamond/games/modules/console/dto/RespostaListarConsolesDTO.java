package diamond.games.modules.console.dto;

import java.time.LocalDate;

import diamond.games.modules.console.entity.Console;

public record RespostaListarConsolesDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String empresa) {
    public RespostaListarConsolesDTO(Console console) {
        this(console.getCodigo(), console.getNome(), console.getDataLancamento(), console.getEmpresa());
    }
}
