package diamond.consoles.modules.jogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.consoles.modules.jogo.dto.CriarJogoDTO;
import diamond.consoles.modules.jogo.dto.RespostaCriarJogoDTO;
import diamond.consoles.modules.jogo.dto.RespostaListarJogoPorCodigoDTO;
import diamond.consoles.modules.jogo.dto.RespostaListarJogosDTO;
import diamond.consoles.modules.jogo.entity.Jogo;
import diamond.consoles.modules.jogo.useCase.CriarJogoUseCase;
import diamond.consoles.modules.jogo.useCase.ListagemPaginadaJogosUseCase;
import diamond.consoles.modules.jogo.useCase.ListarJogoPorCodigoUseCase;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/jogos")
public class JogoController {
    @Autowired
    private CriarJogoUseCase criarJogoUseCase;

    @Autowired
    private ListagemPaginadaJogosUseCase listagemPaginadaJogosUseCase;

    @Autowired
    private ListarJogoPorCodigoUseCase listarJogoPorCodigoUseCase;

    @PostMapping()
    public ResponseEntity<RespostaCriarJogoDTO> create(@Valid @RequestBody CriarJogoDTO criarJogoDTO,
            UriComponentsBuilder uriBuilder) {
        Jogo jogo = this.criarJogoUseCase.execute(criarJogoDTO);

        RespostaCriarJogoDTO resposta = new RespostaCriarJogoDTO(jogo);

        var uri = uriBuilder.path("/jogos/{codigo}").buildAndExpand(jogo.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping()
    public ResponseEntity<Page<RespostaListarJogosDTO>> listarTodos(
            @PageableDefault(size = 5, page = 0, sort = { "codigo" }) Pageable pagination) {
        Page<RespostaListarJogosDTO> response = this.listagemPaginadaJogosUseCase.execute(pagination);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<RespostaListarJogoPorCodigoDTO> listarPorCodigo(@PathVariable Long codigo) {
        RespostaListarJogoPorCodigoDTO response = this.listarJogoPorCodigoUseCase.execute(codigo);
        return ResponseEntity.ok(response);
    }
}
