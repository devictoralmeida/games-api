package diamond.consoles.modules.desenvolvedor.dto;

import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;

public record RespostaDesenvolvedorParcialDTO(
        Long codigo,
        String nome,
        String website) {
    public RespostaDesenvolvedorParcialDTO(Desenvolvedor desenvolvedor) {
        this(desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getWebsite());
    }
}
