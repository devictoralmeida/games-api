package diamond.consoles.modules.desenvolvedor.useCase;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.DeveloperNotFoundException;
import diamond.consoles.modules.desenvolvedor.dto.RespostaListarDesenvolvedorPorCodigoDTO;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;
import diamond.consoles.modules.jogo.dto.RetornoJogoParcialDTO;

@Service
public class ListarDesenvolvedorPorCodigoUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    @Transactional
    public RespostaListarDesenvolvedorPorCodigoDTO execute(Long codigo) {
        var desenvolvedor = this.desenvolvedorRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new DeveloperNotFoundException();
                });


        var jogos = desenvolvedor.getJogos().stream().map(jogo -> new RetornoJogoParcialDTO(jogo)).collect(Collectors.toList());

        return new RespostaListarDesenvolvedorPorCodigoDTO(desenvolvedor, jogos);
    }
}
