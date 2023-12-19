package diamond.games.modules.console.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.games.modules.console.entity.Console;

public interface ConsoleRepositorio extends JpaRepository<Console, Long> {
    Page<Console> findAll(Pageable pageable);

    Optional<Console> findByCodigo(Long codigo);

    Optional<Console> findByNome(String nome);
}
