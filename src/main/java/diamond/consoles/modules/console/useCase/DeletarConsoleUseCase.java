package diamond.consoles.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.console.ConsoleNotFoundException;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;

@Service
public class DeletarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public void execute(Long codigo) {
        Console console = consoleRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ConsoleNotFoundException();
                });
        this.consoleRepositorio.delete(console);

    }
}
