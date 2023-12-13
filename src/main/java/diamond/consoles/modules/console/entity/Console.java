package diamond.consoles.modules.console.entity;

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
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_lancamento")
    private java.util.Date dataLancamento;

    private String empresa;

    @ManyToOne()
    @JoinColumn(name = "jogo", referencedColumnName = "codigo")
    private Jogo jogo;
}
