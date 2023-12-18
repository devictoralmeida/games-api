package diamond.consoles.exceptions.jogo;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException() {
        super("Já existe um jogo cadastrado com esse nome");
    }
}