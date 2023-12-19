package diamond.games.modules.desenvolvedor.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import diamond.games.modules.desenvolvedor.dto.RespostaListarDesenvolvedoresDTO;
import diamond.games.modules.desenvolvedor.repository.DesenvolvedorRepositorio;

@Service
public class ListagemPaginadaDesenvolvedoresUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    public Page<RespostaListarDesenvolvedoresDTO> execute(Pageable pageable) {
        return this.desenvolvedorRepositorio.findAll(pageable).map(
                RespostaListarDesenvolvedoresDTO::new);

    }
}
