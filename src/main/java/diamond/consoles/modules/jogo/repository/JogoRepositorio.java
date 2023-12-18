package diamond.consoles.modules.jogo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.jogo.entity.Jogo;

public interface JogoRepositorio extends JpaRepository<Jogo, Long> {
    Page<Jogo> findAll(Pageable pageable);

    Optional<Jogo> findByCodigo(Long codigo);

    Optional<Jogo> findByNome(String nome);

}
