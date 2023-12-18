package diamond.consoles.modules.jogo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamond.consoles.exceptions.jogo.GameNotFoundException;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.repository.JogoRepositorio;

@Service
public class DeletarJogoUseCase {
    @Autowired
    private JogoRepositorio jogoRepositorio;

    public void execute(Long codigo) {
        Jogo jogo = this.jogoRepositorio.findByCodigo(codigo).orElseThrow(
                () -> {
                    throw new GameNotFoundException();
                });

        this.jogoRepositorio.delete(jogo);
    }
}
