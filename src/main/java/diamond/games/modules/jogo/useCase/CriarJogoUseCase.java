package diamond.games.modules.jogo.useCase;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.games.exceptions.console.ExcessaoConsoleNaoEncontrado;
import diamond.games.exceptions.desenvolvedor.ExcessaoDesenvolvedorNaoEncontrado;
import diamond.games.exceptions.jogo.ExcessaoJogoJaExiste;
import diamond.games.modules.console.entity.Console;
import diamond.games.modules.console.repository.ConsoleRepositorio;
import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.games.modules.desenvolvedor.repository.DesenvolvedorRepositorio;
import diamond.games.modules.jogo.dto.CriarJogoDTO;
import diamond.games.modules.jogo.entity.Jogo;
import diamond.games.modules.jogo.repository.JogoRepositorio;

@Service
public class CriarJogoUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    @Transactional
    public Jogo execute(CriarJogoDTO criarJogoDTO) {

        this.jogoRepositorio.findByNome(criarJogoDTO.nome()).ifPresent(
                jogo -> {
                    throw new ExcessaoJogoJaExiste();
                });

        Long codigoDesenvolvedor = criarJogoDTO.desenvolvedor().get("codigo");

        Desenvolvedor desenvolvedor = this.desenvolvedorRepositorio.findByCodigo(codigoDesenvolvedor).orElseThrow(
                () -> {
                    throw new ExcessaoDesenvolvedorNaoEncontrado();
                });

        Set<Console> consoles = new HashSet<>();

        for (Map<String, Long> item : criarJogoDTO.consoles()) {
            Long codigoConsole = item.get("codigo");

            Console console = this.consoleRepositorio.findByCodigo(codigoConsole).orElseThrow(
                    () -> {
                        throw new ExcessaoConsoleNaoEncontrado();
                    });

            consoles.add(console);
        }

        Jogo jogo = new Jogo(criarJogoDTO);
        jogo.setConsoles(consoles);
        jogo.setDesenvolvedor(desenvolvedor);

        this.jogoRepositorio.save(jogo);

        return jogo;
    }
}
