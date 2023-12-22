package diamond.games.modules.jogo.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.games.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.games.modules.jogo.entity.Jogo;

public record RespostaJogoCompletoDTO(
        Long codigo,
        String nome,
        String descricao,
        LocalDate dataLancamento,
        String website,
        String genero,
        String urlCapa,
        RespostaDesenvolvedorParcialDTO desenvolvedor,
        List<RespostaParcialConsoleDTO> consoles) {
    public RespostaJogoCompletoDTO(Jogo jogo, RespostaDesenvolvedorParcialDTO dadosDesenvolvedor,
            List<RespostaParcialConsoleDTO> dadosConsoles) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getDescricao(), jogo.getDataLancamento(), jogo.getWebsite(),
                jogo.getGenero(), jogo.getUrlCapa(),
                dadosDesenvolvedor, dadosConsoles);
    }
}
