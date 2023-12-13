package diamond.consoles.modules.console.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public Console(CriarConsoleDTO criarConsoleDTO) {
        this.nome = criarConsoleDTO.nome();
        this.dataLancamento = this.gerarDataAtual();
        this.empresa = criarConsoleDTO.empresa();
    }


    public Date gerarDataAtual() {
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataAtual = LocalDate.now().format(formatoDesejado);

        Date date = Date.from(LocalDate.parse(dataAtual, formatoDesejado).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }
}
