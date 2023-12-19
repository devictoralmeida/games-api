package diamond.games.modules.jogo.useCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.games.exceptions.console.ExcessaoConsoleNaoEncontrado;
import diamond.games.exceptions.jogo.ExcessaoJogoJaExiste;
import diamond.games.exceptions.jogo.ExcessaoJogoNaoEncontrado;
import diamond.games.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.games.modules.console.entity.Console;
import diamond.games.modules.console.repository.ConsoleRepositorio;
import diamond.games.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.games.modules.jogo.dto.AtualizarJogoDTO;
import diamond.games.modules.jogo.dto.RespostaJogoCompletoDTO;
import diamond.games.modules.jogo.entity.Jogo;
import diamond.games.modules.jogo.repository.JogoRepositorio;

@Service
public class AtualizarJogoUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public RespostaJogoCompletoDTO execute(Long codigo, AtualizarJogoDTO dadosParaAtualizar) {
        Jogo jogo = this.jogoRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ExcessaoJogoNaoEncontrado();
                });

        if (dadosParaAtualizar.nome() != null && !jogo.getNome().equals(dadosParaAtualizar.nome())) {
            this.jogoRepositorio.findByNome(dadosParaAtualizar.nome()).ifPresent(
                    x -> {
                        throw new ExcessaoJogoJaExiste();
                    });

            jogo.setNome(dadosParaAtualizar.nome());
        }

        if (dadosParaAtualizar.descricao() != null && !jogo.getDescricao().equals(dadosParaAtualizar.descricao())) {
            jogo.setDescricao(dadosParaAtualizar.descricao());
        }

        if (dadosParaAtualizar.website() != null && !jogo.getWebsite().equals(dadosParaAtualizar.website())) {
            jogo.setWebsite(dadosParaAtualizar.website());
        }

        if (dadosParaAtualizar.genero() != null && !jogo.getGenero().equals(dadosParaAtualizar.genero())) {
            jogo.setGenero(dadosParaAtualizar.genero());
        }

        if (dadosParaAtualizar.urlCapa() != null && !jogo.getUrlCapa().equals(dadosParaAtualizar.urlCapa())) {
            jogo.setUrlCapa(dadosParaAtualizar.urlCapa());
        }

        Set<Console> consoleSet = new HashSet<Console>();

        if (dadosParaAtualizar.consoles() != null && dadosParaAtualizar.consoles().size() >= 1) {
            for (Long codigoConsole : dadosParaAtualizar.consoles()) {
                Console console = consoleRepositorio.findByCodigo(codigoConsole).orElseThrow(
                        () -> {
                            throw new ExcessaoConsoleNaoEncontrado();
                        });

                consoleSet.add(console);
            }
        }

        jogo.setConsoles(consoleSet);

        RespostaDesenvolvedorParcialDTO desenvolvedor = new RespostaDesenvolvedorParcialDTO(jogo.getDesenvolvedor());

        List<RespostaParcialConsoleDTO> consoles = jogo.getConsoles().stream()
                .map(console -> new RespostaParcialConsoleDTO(console)).collect(Collectors.toList());

        return new RespostaJogoCompletoDTO(jogo, desenvolvedor, consoles);

    }
}
