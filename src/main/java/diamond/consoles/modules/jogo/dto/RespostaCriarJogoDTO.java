package diamond.consoles.modules.jogo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.shared.dto.RespostaCodigoDTO;

public record RespostaCriarJogoDTO(
        Long codigo,
        String nome,
        String descricao,
        LocalDate dataLancamento,
        String website,
        RespostaCodigoDTO desenvolvedor,
        String genero,
        String urlCapa,
        List<RespostaCodigoDTO> consoles) {

    public RespostaCriarJogoDTO(Jogo jogo) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getDescricao(),jogo.getDataLancamento(), jogo.getWebsite(),
                new RespostaCodigoDTO(jogo.getDesenvolvedor().getCodigo()),
                jogo.getGenero(), jogo.getUrlCapa(), jogo.getConsoles().stream()
                        .map(console -> new RespostaCodigoDTO(console.getCodigo())).collect(Collectors.toList()));
    }
}
