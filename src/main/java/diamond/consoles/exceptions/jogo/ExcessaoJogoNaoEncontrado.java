package diamond.consoles.exceptions.jogo;

public class ExcessaoJogoNaoEncontrado extends RuntimeException {
    public ExcessaoJogoNaoEncontrado() {
        super("Jogo não encontrado");
    }
}
