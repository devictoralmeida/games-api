package diamond.games.modules.jogo.useCase;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.games.exceptions.jogo.ExcessaoJogoNaoEncontrado;
import diamond.games.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.games.modules.jogo.dto.RespostaJogoCompletoDTO;
import diamond.games.modules.jogo.repository.JogoRepositorio;

@Service
public class ListarJogoPorCodigoUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    @Transactional
    public RespostaJogoCompletoDTO execute(Long codigo) {
        var jogo = this.jogoRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ExcessaoJogoNaoEncontrado();
                });

        var desenvolvedor = new RespostaDesenvolvedorParcialDTO(jogo.getDesenvolvedor());

        var consoles = jogo.getConsoles().stream().map(console -> new RespostaParcialConsoleDTO(console))
                .collect(Collectors.toList());

        return new RespostaJogoCompletoDTO(jogo, desenvolvedor, consoles);
    }
}
