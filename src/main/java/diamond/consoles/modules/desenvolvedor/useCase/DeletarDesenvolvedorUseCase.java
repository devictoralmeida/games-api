package diamond.consoles.modules.desenvolvedor.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.DeveloperNotFoundException;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;

@Service
public class DeletarDesenvolvedorUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    @Transactional
    public void execute(Long codigo) {
        Desenvolvedor desenvolvedor = this.desenvolvedorRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new DeveloperNotFoundException();
                });

        this.desenvolvedorRepositorio.delete(desenvolvedor);
    }
}
