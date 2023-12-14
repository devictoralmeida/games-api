package diamond.consoles.modules.desenvolvedor.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "desenvolvedores")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    public Desenvolvedor(CriarDesenvolvedorDTO criarDesenvolvedorDTO) {
        this.nome = criarDesenvolvedorDTO.nome();
        this.dataFundacao = LocalDate.now();
        this.website = criarDesenvolvedorDTO.website() != null ? criarDesenvolvedorDTO.website() : null;
        this.sede = criarDesenvolvedorDTO.sede();
    }
}