package diamond.consoles.modules.desenvolvedor.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.ZoneId;

import org.springframework.data.annotation.CreatedDate;

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
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

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "data_fundacao")
    private java.util.Date dataFundacao;


    private String website;
    private String sede;

    public Desenvolvedor(CriarDesenvolvedorDTO criarDesenvolvedorDTO) {
        this.nome = criarDesenvolvedorDTO.nome();
        this.dataFundacao = this.gerarDataAtual();
        this.website = criarDesenvolvedorDTO.website();
        this.sede = criarDesenvolvedorDTO.sede();
    }

    public Date gerarDataAtual() {
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataAtual = LocalDate.now().format(formatoDesejado);

        Date date = Date.from(LocalDate.parse(dataAtual, formatoDesejado).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }
}