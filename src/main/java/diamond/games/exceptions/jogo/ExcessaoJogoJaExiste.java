package diamond.games.exceptions.jogo;

public class ExcessaoJogoJaExiste extends RuntimeException {
    public ExcessaoJogoJaExiste() {
        super("Já existe um jogo cadastrado com esse nome");
    }
}