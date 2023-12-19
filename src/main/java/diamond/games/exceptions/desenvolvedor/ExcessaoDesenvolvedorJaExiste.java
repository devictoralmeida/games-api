package diamond.games.exceptions.desenvolvedor;

public class ExcessaoDesenvolvedorJaExiste extends RuntimeException {
    public ExcessaoDesenvolvedorJaExiste() {
        super("Já existe um desenvolvedor cadastrado com esse nome");
    }
}
