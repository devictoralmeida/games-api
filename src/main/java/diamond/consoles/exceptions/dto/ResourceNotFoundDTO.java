package diamond.consoles.exceptions.dto;

public class ResourceNotFoundDTO extends RuntimeException {
    public ResourceNotFoundDTO() {
        super("Recurso não encontrado");
    }
}
