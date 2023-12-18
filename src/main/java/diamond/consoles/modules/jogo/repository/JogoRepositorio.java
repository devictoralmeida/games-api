package diamond.consoles.modules.jogo.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.jogo.entity.Jogo;

public interface JogoRepositorio extends JpaRepository<Jogo, Long> {
    Page<Jogo> findAll(Pageable pageable);

    Optional<Jogo> findByCodigo(Long codigo);

    Optional<Jogo> findByNome(String nome);

    //Optional<Set<Jogo>> findAllByCodigo(List<Long> codigos);
}
