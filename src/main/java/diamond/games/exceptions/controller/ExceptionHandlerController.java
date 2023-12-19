package diamond.games.exceptions.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import diamond.games.exceptions.console.ExcessaoConsoleJaExiste;
import diamond.games.exceptions.console.ExcessaoConsoleNaoEncontrado;
import diamond.games.exceptions.desenvolvedor.ExcessaoDesenvolvedorJaExiste;
import diamond.games.exceptions.desenvolvedor.ExcessaoDesenvolvedorNaoEncontrado;
import diamond.games.exceptions.dto.ErroDeValidacaoDTO;
import diamond.games.exceptions.dto.RecursoNaoEncontradoDTO;
import diamond.games.exceptions.dto.RespostaErroDTO;
import diamond.games.exceptions.jogo.ExcessaoJogoJaExiste;
import diamond.games.exceptions.jogo.ExcessaoJogoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroDeValidacaoDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<ErroDeValidacaoDTO> response = new ArrayList<ErroDeValidacaoDTO>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            ErroDeValidacaoDTO error = new ErroDeValidacaoDTO(err.getField(), message);
            response.add(error);
        });

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RespostaErroDTO> resourceNotFound() {
        RecursoNaoEncontradoDTO exception = new RecursoNaoEncontradoDTO();
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ExcessaoJogoNaoEncontrado.class)
    public ResponseEntity<RespostaErroDTO> gameNotFound(ExcessaoJogoNaoEncontrado exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ExcessaoJogoJaExiste.class)
    public ResponseEntity<RespostaErroDTO> gameAlreadyExists(ExcessaoJogoJaExiste exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(ExcessaoDesenvolvedorNaoEncontrado.class)
    public ResponseEntity<RespostaErroDTO> developerNotFound(ExcessaoDesenvolvedorNaoEncontrado exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ExcessaoDesenvolvedorJaExiste.class)
    public ResponseEntity<RespostaErroDTO> developerAlreadyExists(ExcessaoDesenvolvedorJaExiste exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(ExcessaoConsoleNaoEncontrado.class)
    public ResponseEntity<RespostaErroDTO> consoleNotFound(ExcessaoConsoleNaoEncontrado exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ExcessaoConsoleJaExiste.class)
    public ResponseEntity<RespostaErroDTO> consoleAlreadyExists(ExcessaoConsoleJaExiste exception) {
        RespostaErroDTO response = new RespostaErroDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }
}
