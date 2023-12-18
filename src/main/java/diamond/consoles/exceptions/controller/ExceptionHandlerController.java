package diamond.consoles.exceptions.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import diamond.consoles.exceptions.console.ConsoleAlreadyExistsException;
import diamond.consoles.exceptions.console.ConsoleNotFoundException;
import diamond.consoles.exceptions.desenvolvedor.DeveloperAlreadyExistsException;
import diamond.consoles.exceptions.desenvolvedor.ExcessaoDesenvolvedorNaoEncontrado;
import diamond.consoles.exceptions.dto.ErrorResponseDTO;
import diamond.consoles.exceptions.dto.ResourceNotFoundDTO;
import diamond.consoles.exceptions.dto.ValidationErrorDTO;
import diamond.consoles.exceptions.jogo.GameAlreadyExistsException;
import diamond.consoles.exceptions.jogo.GameNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<ValidationErrorDTO> response = new ArrayList<ValidationErrorDTO>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            ValidationErrorDTO error = new ValidationErrorDTO(err.getField(), message);
            response.add(error);
        });

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound() {
        ResourceNotFoundDTO exception = new ResourceNotFoundDTO();
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> gameNotFound(GameNotFoundException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> gameAlreadyExists(GameAlreadyExistsException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(ExcessaoDesenvolvedorNaoEncontrado.class)
    public ResponseEntity<ErrorResponseDTO> developerNotFound(ExcessaoDesenvolvedorNaoEncontrado exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(DeveloperAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> developerAlreadyExists(DeveloperAlreadyExistsException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(ConsoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> consoleNotFound(ConsoleNotFoundException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ConsoleAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> consoleAlreadyExists(ConsoleAlreadyExistsException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }
}
