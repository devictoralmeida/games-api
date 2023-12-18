package diamond.consoles.modules.desenvolvedor.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "desenvolvedores")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Desenvolvedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", updatable = false)
    private Long codigo;

    private String nome;

    @CreatedDate
    @Column(name = "data_fundacao", updatable = false)
    private LocalDate dataFundacao;

    private String website;
    private String sede;

    @OneToMany(mappedBy = "desenvolvedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Jogo> jogos;

    public Desenvolvedor(CriarDesenvolvedorDTO criarDesenvolvedorDTO) {
        this.nome = criarDesenvolvedorDTO.nome();
        this.dataFundacao = LocalDate.now();
        this.website = criarDesenvolvedorDTO.website() != null ? criarDesenvolvedorDTO.website() : null;
        this.sede = criarDesenvolvedorDTO.sede();
    }
}