package diamond.consoles.exceptions;

public class ConsoleNotFoundException extends RuntimeException {
    public ConsoleNotFoundException() {
        super("Console não encontrado");
    }
}