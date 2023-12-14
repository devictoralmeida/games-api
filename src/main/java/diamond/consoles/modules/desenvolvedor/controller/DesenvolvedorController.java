package diamond.consoles.modules.desenvolvedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import diamond.consoles.modules.desenvolvedor.dto.CriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.dto.RespostaCriarDesenvolvedorDTO;
import diamond.consoles.modules.desenvolvedor.entity.Desenvolvedor;
import diamond.consoles.modules.desenvolvedor.useCase.CriarDesenvolvedorUseCase;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/desenvolvedores")
public class DesenvolvedorController {
    @Autowired
    private CriarDesenvolvedorUseCase criarDesenvolvedorUseCase;

    @PostMapping()
    public ResponseEntity<RespostaCriarDesenvolvedorDTO> create(@RequestBody @Valid CriarDesenvolvedorDTO criarDesenvolvedorDTO,
            UriComponentsBuilder uriBuilder) {
        Desenvolvedor desenvolvedor = this.criarDesenvolvedorUseCase.execute(criarDesenvolvedorDTO);

        RespostaCriarDesenvolvedorDTO response = new RespostaCriarDesenvolvedorDTO(desenvolvedor);

        var uri = uriBuilder.path("/desenvolvedores/{codigo}").buildAndExpand(desenvolvedor.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(response);

    }
}
