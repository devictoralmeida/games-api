package diamond.games.modules.desenvolvedor.dto;

import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;

public record RespostaDesenvolvedorParcialDTO(
        Long codigo,
        String nome,
        String website) {
    public RespostaDesenvolvedorParcialDTO(Desenvolvedor desenvolvedor) {
        this(desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getWebsite());
    }
}
