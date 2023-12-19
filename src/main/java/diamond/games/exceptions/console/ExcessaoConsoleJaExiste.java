package diamond.games.exceptions.console;

public class ExcessaoConsoleJaExiste extends RuntimeException {
    public ExcessaoConsoleJaExiste() {
        super("Já existe um console cadastrado com esse nome");
    }
}
