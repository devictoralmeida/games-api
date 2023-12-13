package diamond.consoles.modules.desenvolvedor.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;

@Service
public class CriarDesenvolvedorUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    public Desenvolvedor execute(CriarDesenvolvedorDTO criarDesenvolvedorDTO) {
        Desenvolvedor desenvolvedor = new Desenvolvedor(criarDesenvolvedorDTO);
        return this.desenvolvedorRepositorio.save(desenvolvedor);
    }
}
