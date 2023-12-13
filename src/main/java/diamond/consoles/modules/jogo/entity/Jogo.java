package diamond.consoles.modules.jogo.entity;

import java.util.HashSet;

import java.util.Set;


import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
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
    @JoinColumn(name = "desenvolvedor", referencedColumnName = "codigo")
    private Desenvolvedor desenvolvedor;

    private String genero;

    @Column(name = "url_capa")
    private String urlCapa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogo")
    private Set<Console> consoles = new HashSet<>();
}
