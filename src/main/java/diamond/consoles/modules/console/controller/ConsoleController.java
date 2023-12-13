package diamond.consoles.modules.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.consoles.modules.console.dto.CriarConsoleDTO;
import diamond.consoles.modules.console.entity.Console;
import diamond.consoles.modules.console.useCase.CriarConsoleUseCase;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/consoles")
public class ConsoleController {
    @Autowired
    private CriarConsoleUseCase criarConsoleUseCase;

    @PostMapping()
    public ResponseEntity<Console> create(@Valid @RequestBody CriarConsoleDTO criarConsoleDTO,
            UriComponentsBuilder uriBuilder) {
        Console console = this.criarConsoleUseCase.execute(criarConsoleDTO);

        var uri = uriBuilder.path("/consoles/{codigo}").buildAndExpand(console.getCodigo()).toUri();
        
        return ResponseEntity.created(uri).body(console);
    }
}
