package diamond.games.modules.jogo.dto;

import diamond.games.modules.jogo.entity.Jogo;

public record RetornoJogoParcialDTO(
    Long codigo,
    String nome,
    String genero
) {
    public RetornoJogoParcialDTO(Jogo jogo) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getGenero());
    }
}
