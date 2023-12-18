package diamond.consoles.modules.jogo.useCase;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.console.ConsoleNotFoundException;
import diamond.consoles.exceptions.desenvolvedor.ExcessaoDesenvolvedorNaoEncontrado;
import diamond.consoles.exceptions.jogo.GameAlreadyExistsException;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;
import diamond.consoles.modules.jogo.dto.CriarJogoDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.repository.JogoRepositorio;

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
                    throw new GameAlreadyExistsException();
                });

        Long codigoDesenvolvedor = criarJogoDTO.desenvolvedor().get("codigo");

        var desenvolvedor = this.desenvolvedorRepositorio.findByCodigo(codigoDesenvolvedor).orElseThrow(
                () -> {
                    throw new ExcessaoDesenvolvedorNaoEncontrado();
                });

        Set<Console> consoles = new HashSet<>();

        for (Map<String, Long> item : criarJogoDTO.consoles()) {
            Long codigoConsole = item.get("codigo");

            var console = this.consoleRepositorio.findByCodigo(codigoConsole).orElseThrow(
                    () -> {
                        throw new ConsoleNotFoundException();
                    });

            consoles.add(console);
        }

        Jogo jogoEntity = Jogo.builder()
                .nome(criarJogoDTO.nome())
                .descricao(criarJogoDTO.descricao())
                .dataLancamento(LocalDate.now())
                .website(criarJogoDTO.website())
                .desenvolvedor(desenvolvedor)
                .genero(criarJogoDTO.genero())
                .urlCapa(criarJogoDTO.urlCapa())
                .consoles(consoles)
                .build();

        this.jogoRepositorio.save(jogoEntity);

        return jogoEntity;
    }
}
