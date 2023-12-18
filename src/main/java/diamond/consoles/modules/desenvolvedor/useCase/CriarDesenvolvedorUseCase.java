package diamond.consoles.modules.desenvolvedor.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.desenvolvedor.DeveloperAlreadyExistsException;
import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;

@Service
public class CriarDesenvolvedorUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    @Transactional
    public Desenvolvedor execute(CriarDesenvolvedorDTO criarDesenvolvedorDTO) {
        this.desenvolvedorRepositorio.findByNome(criarDesenvolvedorDTO.nome()).ifPresent(
            desenvolvedor -> {
                throw new DeveloperAlreadyExistsException();
            }
        );

        Desenvolvedor desenvolvedor = new Desenvolvedor(criarDesenvolvedorDTO);
        var desenvolvedorEntidade = this.desenvolvedorRepositorio.save(desenvolvedor);
        desenvolvedor.setCodigo(desenvolvedorEntidade.getCodigo());


        return desenvolvedor;
    }
}
