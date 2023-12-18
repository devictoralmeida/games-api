package diamond.consoles.exceptions.jogo;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Jogo não encontrado");
    }
}
