package diamond.consoles.exceptions.desenvolvedor;

public class ExcessaoDesenvolvedorNaoEncontrado extends RuntimeException {
    public ExcessaoDesenvolvedorNaoEncontrado() {
        super("Desenvolvedor não encontrado");
    }
}
