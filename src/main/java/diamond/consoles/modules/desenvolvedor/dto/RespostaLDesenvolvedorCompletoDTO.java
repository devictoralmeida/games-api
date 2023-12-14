package diamond.consoles.modules.desenvolvedor.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.jogo.dto.RetornoJogoParcialDTO;

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