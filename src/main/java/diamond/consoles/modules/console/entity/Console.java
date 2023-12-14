package diamond.consoles.modules.console.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import diamond.consoles.modules.console.dto.CriarConsoleDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "consoles")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", updatable = false)
    private Long codigo;

    private String nome;

    @CreatedDate
    @Column(name = "data_lancamento", updatable = false)
    private LocalDate dataLancamento;

    private String empresa;

    @ManyToMany(mappedBy = "consoles")
    private Set<Jogo> jogos;

    public Console(CriarConsoleDTO criarConsoleDTO) {
        this.nome = criarConsoleDTO.nome();
        this.dataLancamento = LocalDate.now();
        this.empresa = criarConsoleDTO.empresa();
    }
}
