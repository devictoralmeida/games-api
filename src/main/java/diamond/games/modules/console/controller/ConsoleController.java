package diamond.games.modules.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.games.modules.console.dto.AtualizarConsoleDTO;
import diamond.games.modules.console.dto.CriarConsoleDTO;
import diamond.games.modules.console.dto.RespostaConsoleCompletoDTO;
import diamond.games.modules.console.dto.RespostaCriarConsoleDTO;
import diamond.games.modules.console.dto.RespostaListarConsolesDTO;
import diamond.games.modules.console.entity.Console;
import diamond.games.modules.console.useCase.AtualizarConsoleUseCase;
import diamond.games.modules.console.useCase.CriarConsoleUseCase;
import diamond.games.modules.console.useCase.DeletarConsoleUseCase;
import diamond.games.modules.console.useCase.ListagemPaginadaConsolesUseCase;
import diamond.games.modules.console.useCase.ListarConsolePorCodigoUseCase;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/consoles")
public class ConsoleController {
    @Autowired
    private CriarConsoleUseCase criarConsoleUseCase;

    @Autowired
    private ListagemPaginadaConsolesUseCase listagemPaginadaConsolesUseCase;

    @Autowired
    private ListarConsolePorCodigoUseCase listarConsolePorCodigoUseCase;

    @Autowired
    private AtualizarConsoleUseCase atualizarConsoleUseCase;

    @Autowired
    private DeletarConsoleUseCase deletarConsoleUseCase;

    @PostMapping()
    public ResponseEntity<RespostaCriarConsoleDTO> create(@RequestBody @Valid CriarConsoleDTO criarConsoleDTO,
            UriComponentsBuilder uriBuilder) {
        Console console = this.criarConsoleUseCase.execute(criarConsoleDTO);

        RespostaCriarConsoleDTO resposta = new RespostaCriarConsoleDTO(console);

        var uri = uriBuilder.path("/consoles/{codigo}").buildAndExpand(console.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping()
    public ResponseEntity<Page<RespostaListarConsolesDTO>> listarTodos(
            @PageableDefault(size = 10, page = 0, sort = { "codigo" }) Pageable pagination) {
        Page<RespostaListarConsolesDTO> response = this.listagemPaginadaConsolesUseCase.execute(pagination);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<RespostaConsoleCompletoDTO> listarPorCodigo(@PathVariable Long codigo) {
        RespostaConsoleCompletoDTO response = this.listarConsolePorCodigoUseCase.execute(codigo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<RespostaConsoleCompletoDTO> update(@PathVariable Long codigo,
            @RequestBody @Valid AtualizarConsoleDTO dadosParaAtualizar) {
        RespostaConsoleCompletoDTO response = this.atualizarConsoleUseCase.execute(codigo, dadosParaAtualizar);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> destroy(@PathVariable Long codigo) {
        this.deletarConsoleUseCase.execute(codigo);
        return ResponseEntity.noContent().build();
    }
}
