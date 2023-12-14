package diamond.consoles.exceptions;

public class DeveloperAlreadyExistsException extends RuntimeException {
    public DeveloperAlreadyExistsException() {
        super("Já existe um desenvolvedor cadastrado com esse nome");
    }
}
