package diamond.consoles.modules.desenvolvedor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "desenvolvedores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Desenvolvedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String website;
    private String sede;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fundacao")
    private java.util.Date dataFundacao;
}
