package diamond.consoles.exceptions.console;

public class ExcessaoConsoleNaoEncontrado extends RuntimeException {
    public ExcessaoConsoleNaoEncontrado() {
        super("Console não encontrado");
    }
}