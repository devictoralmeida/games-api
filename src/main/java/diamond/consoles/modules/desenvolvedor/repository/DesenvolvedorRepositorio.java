package diamond.consoles.modules.desenvolvedor.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;

public interface DesenvolvedorRepositorio extends JpaRepository<Desenvolvedor, Long> {
    Page<Desenvolvedor> findAll(Pageable pageable);

    Optional<Desenvolvedor> findByCodigo(Long codigo);

    Optional<Desenvolvedor> findByNome(String nome);
}
