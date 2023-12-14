package diamond.consoles.modules.desenvolvedor.controller;

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

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaCriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaListarDesenvolvedorPorCodigoDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaListarDesenvolvedoresDTO;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.useCase.CriarDesenvolvedorUseCase;
import diamond.consoles.modules.desenvolvedor.useCase.ListarDesenvolvedorPorCodigoUseCase;
import diamond.consoles.modules.desenvolvedor.useCase.ListagemPaginadaDesenvolvedoresUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
@RequestMapping("/desenvolvedores")
public class DesenvolvedorController {
    @Autowired
    private CriarDesenvolvedorUseCase criarDesenvolvedorUseCase;

    @Autowired
    private ListagemPaginadaDesenvolvedoresUseCase listarTodosDesenvolvedoresUseCase;

    @Autowired
    private ListarDesenvolvedorPorCodigoUseCase listarDesenvolvedorPorCodigoUseCase;

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
    public ResponseEntity<RespostaListarDesenvolvedorPorCodigoDTO> listarPorCodigo(
            @PathVariable Long codigo) {
        RespostaListarDesenvolvedorPorCodigoDTO response = this.listarDesenvolvedorPorCodigoUseCase.execute(codigo);
        return ResponseEntity.ok(response);
    }

}
