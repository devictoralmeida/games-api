package diamond.consoles.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.ConsoleAlreadyExistsException;
import diamond.consoles.modules.console.dto.CriarConsoleDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.repository.ConsoleRepositorio;

@Service
public class CriarConsoleUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    @Transactional
    public Console execute(CriarConsoleDTO criarConsoleDTO) {
        this.consoleRepositorio.findByNome(criarConsoleDTO.nome()).ifPresent(
            console -> {
                throw new ConsoleAlreadyExistsException();
            }
        );

        //Console console = new Console(criarConsoleDTO);
        Console console = this.consoleRepositorio.save(new Console(criarConsoleDTO));
        //console.setCodigo(consoleEntidade.getCodigo());

        return console;
    }
}
