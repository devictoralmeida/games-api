package diamond.consoles.modules.desenvolvedor.useCase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.DeveloperAlreadyExistsException;
import diamond.consoles.exceptions.DeveloperNotFoundException;
import diamond.consoles.modules.desenvolvedor.dto.AtualizarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaLDesenvolvedorCompletoDTO;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.repository.DesenvolvedorRepositorio;
import diamond.consoles.modules.jogo.dto.RetornoJogoParcialDTO;

@Service
public class AtualizarDesenvolvedorUseCase {
    @Autowired
    private DesenvolvedorRepositorio desenvolvedorRepositorio;

    @Transactional
    public RespostaLDesenvolvedorCompletoDTO execute(Long codigo, AtualizarDesenvolvedorDTO dadosParaAtualizar) {
        Desenvolvedor desenvolvedor = this.desenvolvedorRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new DeveloperNotFoundException();
                });

        if (dadosParaAtualizar.nome() != null && !dadosParaAtualizar.nome().equals(desenvolvedor.getNome())) {
            this.desenvolvedorRepositorio.findByNome(dadosParaAtualizar.nome()).ifPresent(
                    x -> {
                        throw new DeveloperAlreadyExistsException();
                    });
        }

        desenvolvedor.update(dadosParaAtualizar);

        List<RetornoJogoParcialDTO> jogos = desenvolvedor.getJogos().stream()
                .map(jogo -> new RetornoJogoParcialDTO(jogo)).collect(Collectors.toList());

        return new RespostaLDesenvolvedorCompletoDTO(desenvolvedor, jogos);
    }
}