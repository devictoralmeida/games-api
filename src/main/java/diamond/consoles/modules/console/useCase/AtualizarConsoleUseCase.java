package diamond.consoles.modules.console.useCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.console.ExcessaoConsoleJaExiste;
import diamond.consoles.exceptions.console.ExcessaoConsoleNaoEncontrado;
import diamond.consoles.exceptions.jogo.ExcessaoJogoNaoEncontrado;
import diamond.consoles.modules.console.dto.AtualizarConsoleDTO;
import diamond.consoles.modules.console.dto.RespostaConsoleCompletoDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;
import diamond.consoles.modules.jogo.dto.RetornoJogoParcialDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.repository.JogoRepositorio;

@Service
public class AtualizarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Autowired
    private JogoRepositorio jogoRepositorio;

    @Transactional
    public RespostaConsoleCompletoDTO execute(Long codigo, AtualizarConsoleDTO atualizarConsoleDTO) {
        Console console = consoleRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ExcessaoConsoleNaoEncontrado();
                });

        if (atualizarConsoleDTO.nome() != null && !console.getNome().equals(atualizarConsoleDTO.nome())) {

            this.consoleRepositorio.findByNome(atualizarConsoleDTO.nome()).ifPresent(
                    x -> {
                        throw new ExcessaoConsoleJaExiste();
                    });

            console.setNome(atualizarConsoleDTO.nome());
        }

        Set<Jogo> jogos = new HashSet<Jogo>();

        if (atualizarConsoleDTO.jogos() != null && atualizarConsoleDTO.jogos().size() >= 1) {
            for (Long codigoJogo : atualizarConsoleDTO.jogos()) {
                Jogo jogo = jogoRepositorio.findByCodigo(codigoJogo).orElseThrow(
                        () -> {
                            throw new ExcessaoJogoNaoEncontrado();
                        });

                jogos.add(jogo);

            }

        }

        console.setJogos(jogos);

        List<RetornoJogoParcialDTO> jogosList = console.getJogos().stream()
                .map(jogo -> new RetornoJogoParcialDTO(jogo)).collect(Collectors.toList());

        return new RespostaConsoleCompletoDTO(console, jogosList);
    }
}
