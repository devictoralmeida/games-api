package diamond.consoles.modules.console.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.console.entity.Console;

public interface ConsoleRepositorio extends JpaRepository<Console, Long> {
    Page<Console> findAll(Pageable pageable);
}
