package diamond.consoles.exceptions.console;

public class ConsoleNotFoundException extends RuntimeException {
    public ConsoleNotFoundException() {
        super("Console não encontrado");
    }
}