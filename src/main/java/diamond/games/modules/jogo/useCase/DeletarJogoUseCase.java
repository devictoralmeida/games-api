package diamond.games.modules.jogo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamond.games.exceptions.jogo.ExcessaoJogoNaoEncontrado;
import diamond.games.modules.jogo.entity.Jogo;
import diamond.games.modules.jogo.repository.JogoRepositorio;

@Service
public class DeletarJogoUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    public void execute(Long codigo) {
        Jogo jogo = this.jogoRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new ExcessaoJogoNaoEncontrado();
                });

        this.jogoRepositorio.delete(jogo);
    }
}
