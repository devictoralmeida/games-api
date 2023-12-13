package diamond.consoles.modules.desenvolvedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;

public interface DesenvolvedorRepositorio extends JpaRepository<Desenvolvedor, Long> {
    
}
