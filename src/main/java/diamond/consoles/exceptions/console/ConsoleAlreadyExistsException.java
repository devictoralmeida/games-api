package diamond.consoles.exceptions.console;

public class ConsoleAlreadyExistsException extends RuntimeException {
    public ConsoleAlreadyExistsException() {
        super("Já existe um console cadastrado com esse nome");
    }
}
