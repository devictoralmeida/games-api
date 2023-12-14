package diamond.consoles.modules.jogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.consoles.modules.jogo.dto.CriarJogoDTO;
import diamond.consoles.modules.jogo.dto.RespostaCriarJogoDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.useCase.CriarJogoUseCase;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/jogos")
public class JogoController {
    @Autowired
    private CriarJogoUseCase criarJogoUseCase;

    @PostMapping()
    public ResponseEntity<RespostaCriarJogoDTO> create(@Valid @RequestBody CriarJogoDTO criarJogoDTO,
            UriComponentsBuilder uriBuilder) {
        Jogo jogo = this.criarJogoUseCase.execute(criarJogoDTO);

        RespostaCriarJogoDTO resposta = new RespostaCriarJogoDTO(jogo);

        var uri = uriBuilder.path("/jogos/{codigo}").buildAndExpand(jogo.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(resposta);
    }
}
