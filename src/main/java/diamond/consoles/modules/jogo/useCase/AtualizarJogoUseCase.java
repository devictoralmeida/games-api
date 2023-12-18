package diamond.consoles.modules.jogo.useCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.console.ConsoleNotFoundException;
import diamond.consoles.exceptions.jogo.GameAlreadyExistsException;
import diamond.consoles.exceptions.jogo.GameNotFoundException;
import diamond.consoles.modules.console.dto.RespostaParcialConsoleDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;
import diamond.consoles.modules.desenvolvedor.dto.RespostaDesenvolvedorParcialDTO;
import diamond.consoles.modules.jogo.dto.AtualizarJogoDTO;
import diamond.consoles.modules.jogo.dto.RespostaJogoCompletoDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.repository.JogoRepositorio;

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
                    throw new GameNotFoundException();
                });

        if (dadosParaAtualizar.nome() != null && !jogo.getNome().equals(dadosParaAtualizar.nome())) {
            this.jogoRepositorio.findByNome(dadosParaAtualizar.nome()).ifPresent(
                    x -> {
                        throw new GameAlreadyExistsException();
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
                            throw new ConsoleNotFoundException();
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
