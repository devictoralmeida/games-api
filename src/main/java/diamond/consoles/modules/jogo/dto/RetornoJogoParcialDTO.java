package diamond.consoles.modules.jogo.dto;

import diamond.consoles.modules.jogo.entity.Jogo;

public record RetornoJogoParcialDTO(
    Long codigo,
    String nome,
    String genero
) {
    public RetornoJogoParcialDTO(Jogo jogo) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getGenero());
    }
}
