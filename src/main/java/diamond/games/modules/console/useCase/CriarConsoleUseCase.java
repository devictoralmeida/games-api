package diamond.games.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.games.exceptions.console.ExcessaoConsoleJaExiste;
import diamond.games.modules.console.dto.CriarConsoleDTO;
import diamond.games.modules.console.entity.Console;
import diamond.games.modules.console.repository.ConsoleRepositorio;

@Service
public class CriarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public Console execute(CriarConsoleDTO criarConsoleDTO) {
        this.consoleRepositorio.findByNome(criarConsoleDTO.nome()).ifPresent(
                console -> {
                    throw new ExcessaoConsoleJaExiste();
                });

        Console console = this.consoleRepositorio.save(new Console(criarConsoleDTO));

        return console;
    }
}
