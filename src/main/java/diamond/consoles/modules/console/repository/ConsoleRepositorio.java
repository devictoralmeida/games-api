package diamond.consoles.modules.console.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.console.entity.Console;

public interface ConsoleRepositorio extends JpaRepository<Console, Long> {
    Page<Console> findAll(Pageable pageable);

    Optional<Console> findByCodigo(Long codigo);

    Optional<Console> findByNome(String nome);

    //Optional<Set<Console>> findAllByCodigo(List<Long> codigos);
}
