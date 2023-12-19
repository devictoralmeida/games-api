package diamond.games.modules.desenvolvedor.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.games.modules.jogo.dto.RetornoJogoParcialDTO;

public record RespostaLDesenvolvedorCompletoDTO(
        Long codigo,
        String nome,
        LocalDate dataFundacao,
        String website,
        String sede,
        List<RetornoJogoParcialDTO> jogos) {
    public RespostaLDesenvolvedorCompletoDTO(Desenvolvedor desenvolvedor, List<RetornoJogoParcialDTO> games) {
        this(desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getDataFundacao(),
                desenvolvedor.getWebsite(), desenvolvedor.getSede(), games);
    }
}