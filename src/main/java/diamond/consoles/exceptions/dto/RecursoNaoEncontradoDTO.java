package diamond.consoles.exceptions.dto;

public class RecursoNaoEncontradoDTO extends RuntimeException {
    public RecursoNaoEncontradoDTO() {
        super("Recurso não encontrado");
    }
}
