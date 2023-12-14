package diamond.consoles.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.modules.console.dto.CriarConsoleDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;

@Service
public class CriarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public Console execute(CriarConsoleDTO criarConsoleDTO) {
        Console console = new Console(criarConsoleDTO);
        var consoleEntidade = this.consoleRepositorio.save(console);
        console.setCodigo(consoleEntidade.getCodigo());

        return console;
    }
}
