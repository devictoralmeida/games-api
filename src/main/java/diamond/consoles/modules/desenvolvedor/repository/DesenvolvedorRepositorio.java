package diamond.consoles.modules.desenvolvedor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;

public interface DesenvolvedorRepositorio extends JpaRepository<Desenvolvedor, Long> {
    Page<Desenvolvedor> findAll(Pageable pageable);
}
