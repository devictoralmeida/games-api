package diamond.consoles.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Jogo não encontrado");
    }
}
