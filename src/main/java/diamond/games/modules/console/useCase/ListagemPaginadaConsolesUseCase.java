package diamond.games.modules.console.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import diamond.games.modules.console.dto.RespostaListarConsolesDTO;
import diamond.games.modules.console.repository.ConsoleRepositorio;

@Service
public class ListagemPaginadaConsolesUseCase {
    @Autowired
    private ConsoleRepositorio consoleRepositorio;

    public Page<RespostaListarConsolesDTO> execute(Pageable pageable) {
        return this.consoleRepositorio.findAll(pageable).map(RespostaListarConsolesDTO::new);
    }
}
