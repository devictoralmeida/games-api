package diamond.consoles.exceptions;

public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException() {
        super("Desenvolvedor não encontrado");
    }
}
