package diamond.consoles.modules.jogo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import diamond.consoles.modules.jogo.dto.RespostaListarJogosDTO;
import diamond.consoles.modules.jogo.repository.JogoRepositorio;

@Service
public class ListagemPaginadaJogosUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    public Page<RespostaListarJogosDTO> execute(Pageable pageable) {
        return this.jogoRepositorio.findAll(pageable).map(RespostaListarJogosDTO::new);
    }
}
