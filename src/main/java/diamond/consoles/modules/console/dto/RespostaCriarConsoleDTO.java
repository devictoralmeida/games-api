package diamond.consoles.modules.console.dto;

import java.time.LocalDate;

import diamond.consoles.modules.console.entity.Console;

public record RespostaCriarConsoleDTO(
        Long codigo,
        String nome,
        LocalDate dataLacamento,
        String empresa) {
    public RespostaCriarConsoleDTO(Console console) {
        this(console.getCodigo(), console.getNome(), console.getDataLancamento(), console.getEmpresa());
    }
}
