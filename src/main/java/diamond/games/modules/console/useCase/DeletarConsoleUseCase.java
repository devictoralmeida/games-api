package diamond.games.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.games.exceptions.console.ExcessaoConsoleNaoEncontrado;
import diamond.games.modules.console.entity.Console;
import diamond.games.modules.console.repository.ConsoleRepositorio;

@Service
public class DeletarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public void execute(Long codigo) {
        Console console = consoleRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ExcessaoConsoleNaoEncontrado();
                });
        this.consoleRepositorio.delete(console);

    }
}
