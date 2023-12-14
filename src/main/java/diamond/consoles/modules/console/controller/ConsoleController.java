package diamond.consoles.modules.console.controller;

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

import diamond.consoles.modules.console.dto.CriarConsoleDTO;
import diamond.consoles.modules.console.dto.RespostaCriarConsoleDTO;
import diamond.consoles.modules.console.dto.RespostaListarConsolePorCodigoDTO;
import diamond.consoles.modules.console.dto.RespostaListarConsolesDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.useCase.CriarConsoleUseCase;
import diamond.consoles.modules.console.useCase.ListagemPaginadaConsolesUseCase;
import diamond.consoles.modules.console.useCase.ListarConsolePorCodigoUseCase;
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
    public ResponseEntity<RespostaListarConsolePorCodigoDTO> listarPorCodigo(@PathVariable Long codigo) {
        RespostaListarConsolePorCodigoDTO response = this.listarConsolePorCodigoUseCase.execute(codigo);
        return ResponseEntity.ok(response);
    }
}
