package diamond.consoles.modules.jogo.dto;

import java.time.LocalDate;
import java.util.List;

import diamond.consoles.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.consoles.modules.jogo.entity.Jogo;

public record RespostaListarJogoPorCodigoDTO(
        Long codigo,
        String nome,
        LocalDate dataLancamento,
        String website,
        String genero,
        RespostaDesenvolvedorParcialDTO desenvolvedor,
        List<RespostaParcialConsoleDTO> consoles) {
    public RespostaListarJogoPorCodigoDTO(Jogo jogo, RespostaDesenvolvedorParcialDTO dadosDesenvolvedor,
            List<RespostaParcialConsoleDTO> dadosConsoles) {
        this(jogo.getCodigo(), jogo.getNome(), jogo.getDataLancamento(), jogo.getWebsite(), jogo.getGenero(),
                dadosDesenvolvedor, dadosConsoles);
    }
}
