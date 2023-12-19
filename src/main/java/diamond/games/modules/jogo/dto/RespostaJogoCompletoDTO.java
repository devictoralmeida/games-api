package diamond.games.modules.jogo.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.games.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.games.modules.jogo.entity.Jogo;

public record RespostaJogoCompletoDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String website,
        String genero,
        RespostaDesenvolvedorParcialDTO desenvolvedor,
        List<RespostaParcialConsoleDTO> consoles) {
    public RespostaJogoCompletoDTO(Jogo jogo, RespostaDesenvolvedorParcialDTO dadosDesenvolvedor,
            List<RespostaParcialConsoleDTO> dadosConsoles) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getDataLancamento(), jogo.getWebsite(), jogo.getGenero(),
                dadosDesenvolvedor, dadosConsoles);
    }
}
