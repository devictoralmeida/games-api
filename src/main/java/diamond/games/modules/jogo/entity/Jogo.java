package diamond.games.modules.jogo.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import diamond.games.modules.console.entity.Console;
import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.games.modules.jogo.dto.CriarJogoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "jogos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", updatable = false)
    private Long codigo;

    private String nome;
    private String descricao;

    @CreatedDate
    @Column(name = "data_lancamento", updatable = false)
    private LocalDate dataLancamento;

    private String website;

    @ManyToOne
    @JoinColumn(name = "desenvolvedor", referencedColumnName = "codigo", updatable = false, nullable = false)
    private Desenvolvedor desenvolvedor;

    private String genero;

    @Column(name = "url_capa")
    private String urlCapa;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jogo_console", joinColumns = @JoinColumn(name = "jogo_codigo"), inverseJoinColumns = @JoinColumn(name = "console_codigo"))
    private Set<Console> consoles = new HashSet<Console>();

    public Jogo(CriarJogoDTO criarJogoDTO) {
        this.nome = criarJogoDTO.nome();

        this.descricao = criarJogoDTO.descricao() != null ? criarJogoDTO.descricao() : null;

        this.dataLancamento = LocalDate.now();
        this.website = criarJogoDTO.website() != null ? criarJogoDTO.website() : null;
        this.genero = criarJogoDTO.genero();
        this.urlCapa = criarJogoDTO.urlCapa() != null ? criarJogoDTO.urlCapa() : null;
    }
}
