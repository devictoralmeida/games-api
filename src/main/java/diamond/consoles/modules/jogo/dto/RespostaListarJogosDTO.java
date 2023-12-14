package diamond.consoles.modules.jogo.dto;

import java.time.LocalDate;

import diamond.consoles.modules.jogo.entity.Jogo;

public record RespostaListarJogosDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String website,
        String genero) {
    public RespostaListarJogosDTO(Jogo jogo) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getDataLancamento(),
                jogo.getWebsite(), jogo.getGenero());
    }
}
