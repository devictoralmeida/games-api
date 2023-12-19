package diamond.games.modules.desenvolvedor.dto;

import java.time.LocalDate;

import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;

public record RespostaListarDesenvolvedoresDTO(
        Long codigo,
        String nome,
        LocalDate dataFundacao,
        String website,
        String sede) {
    public RespostaListarDesenvolvedoresDTO(Desenvolvedor desenvolvedor) {
        this(desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getDataFundacao(),
                desenvolvedor.getWebsite(), desenvolvedor.getSede());
    }
}
