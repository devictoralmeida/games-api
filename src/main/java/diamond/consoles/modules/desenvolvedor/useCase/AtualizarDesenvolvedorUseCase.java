package diamond.consoles.modules.desenvolvedor.useCase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diamond.consoles.exceptions.desenvolvedor.ExcessaoDesenvolvedorJaExiste;
import diamond.consoles.exceptions.desenvolvedor.ExcessaoDesenvolvedorNaoEncontrado;
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
                    throw new ExcessaoDesenvolvedorNaoEncontrado();
                });

        if (dadosParaAtualizar.nome() != null && !dadosParaAtualizar.nome().equals(desenvolvedor.getNome())) {
            this.desenvolvedorRepositorio.findByNome(dadosParaAtualizar.nome()).ifPresent(
                    x -> {
                        throw new ExcessaoDesenvolvedorJaExiste();
                    });
        }

        if (dadosParaAtualizar.website() != null && !desenvolvedor.getWebsite().equals(dadosParaAtualizar.website())) {
            desenvolvedor.setWebsite(dadosParaAtualizar.website());
        }

        if (dadosParaAtualizar.sede() != null && !desenvolvedor.getSede().equals(dadosParaAtualizar.sede())) {
            desenvolvedor.setSede(dadosParaAtualizar.sede());
        }

        List<RetornoJogoParcialDTO> jogos = desenvolvedor.getJogos().stream()
                .map(jogo -> new RetornoJogoParcialDTO(jogo)).collect(Collectors.toList());

        return new RespostaLDesenvolvedorCompletoDTO(desenvolvedor, jogos);
    }
}