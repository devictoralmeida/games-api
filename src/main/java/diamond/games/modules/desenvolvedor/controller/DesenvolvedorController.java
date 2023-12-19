package diamond.games.modules.desenvolvedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.games.modules.desenvolvedor.dto.AtualizarDesenvolvedorDTO;
import diamond.games.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaCriarDesenvolvedorDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaLDesenvolvedorCompletoDTO;
import diamond.games.modules.desenvolvedor.dto.RespostaListarDesenvolvedoresDTO;
import diamond.games.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.games.modules.desenvolvedor.useCase.AtualizarDesenvolvedorUseCase;
import diamond.games.modules.desenvolvedor.useCase.CriarDesenvolvedorUseCase;
import diamond.games.modules.desenvolvedor.useCase.DeletarDesenvolvedorUseCase;
import diamond.games.modules.desenvolvedor.useCase.ListagemPaginadaDesenvolvedoresUseCase;
import diamond.games.modules.desenvolvedor.useCase.ListarDesenvolvedorPorCodigoUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController()
@RequestMapping("/desenvolvedores")
public class DesenvolvedorController {
    @Autowired
    private CriarDesenvolvedorUseCase criarDesenvolvedorUseCase;

    @Autowired
    private ListagemPaginadaDesenvolvedoresUseCase listarTodosDesenvolvedoresUseCase;

    @Autowired
    private ListarDesenvolvedorPorCodigoUseCase listarDesenvolvedorPorCodigoUseCase;

    @Autowired
    private AtualizarDesenvolvedorUseCase atualizarDesenvolvedorUseCase;

    @Autowired
    private DeletarDesenvolvedorUseCase deletarDesenvolvedorUseCase;

    @PostMapping()
    public ResponseEntity<RespostaCriarDesenvolvedorDTO> create(
            @RequestBody @Valid CriarDesenvolvedorDTO criarDesenvolvedorDTO,
            UriComponentsBuilder uriBuilder) {
        Desenvolvedor desenvolvedor = this.criarDesenvolvedorUseCase.execute(criarDesenvolvedorDTO);

        RespostaCriarDesenvolvedorDTO response = new RespostaCriarDesenvolvedorDTO(desenvolvedor);

        var uri = uriBuilder.path("/desenvolvedores/{codigo}").buildAndExpand(desenvolvedor.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping()
    public ResponseEntity<Page<RespostaListarDesenvolvedoresDTO>> ListarTodos(
            @PageableDefault(size = 10, page = 0, sort = { "codigo" }) Pageable pagination) {
        Page<RespostaListarDesenvolvedoresDTO> response = this.listarTodosDesenvolvedoresUseCase.execute(pagination);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<RespostaLDesenvolvedorCompletoDTO> listarPorCodigo(
            @PathVariable Long codigo) {
        RespostaLDesenvolvedorCompletoDTO response = this.listarDesenvolvedorPorCodigoUseCase.execute(codigo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<RespostaLDesenvolvedorCompletoDTO> update(@PathVariable Long codigo,
            @RequestBody @Valid AtualizarDesenvolvedorDTO dadosParaAtualizar) {
        RespostaLDesenvolvedorCompletoDTO response = this.atualizarDesenvolvedorUseCase.execute(codigo,
                dadosParaAtualizar);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> destroy(@PathVariable Long codigo) {
        this.deletarDesenvolvedorUseCase.execute(codigo);
        return ResponseEntity.noContent().build();
    }

}
