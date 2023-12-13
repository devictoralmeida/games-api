package diamond.consoles.modules.jogo.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.jogo.dto.CriarJogoDTO;
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
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_lancamento")
    private java.util.Date dataLancamento;

    private String website;

    @ManyToOne
    @JoinColumn(name = "desenvolvedor", referencedColumnName = "codigo", insertable = false, updatable = false)
    private Desenvolvedor desenvolvedorCodigo;

    @Column(name = "desenvolvedor", nullable = false)
    private Map<String, Long> desenvolvedor;

    private String genero;

    @Column(name = "url_capa")
    private String urlCapa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogo")
    private Set<Console> consolesCodigo = new HashSet<>();

    @Column(name = "consoles", nullable = false)
    private List<Map<String, Long>> consoles;

    public Jogo(CriarJogoDTO criarJogoDTO) {
        this.nome = criarJogoDTO.nome();
        this.descricao = criarJogoDTO.descricao();
        this.dataLancamento = this.gerarDataAtual();
        this.website = criarJogoDTO.website();
        this.desenvolvedor = criarJogoDTO.desenvolvedor();
        this.genero = criarJogoDTO.genero();
        this.urlCapa = criarJogoDTO.urlCapa();
        this.consoles = criarJogoDTO.consoles();
    }

    public Date gerarDataAtual() {
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataAtual = LocalDate.now().format(formatoDesejado);

        Date date = Date.from(LocalDate.parse(dataAtual, formatoDesejado).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }
}
