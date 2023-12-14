package diamond.consoles.modules.console.dto;

import java.time.LocalDate;

import diamond.consoles.modules.console.entity.Console;

public record RespostaParcialConsoleDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento) {
    public RespostaParcialConsoleDTO(Console console) {
        this(console.getCodigo(), console.getNome(), console.getDataLancamento());
    }
}
