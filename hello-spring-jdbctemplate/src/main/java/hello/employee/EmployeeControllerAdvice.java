package hello.employee;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.UUID;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler
    @SneakyThrows
    public ProblemDetail handleException(IllegalArgumentException e) {
        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                e.getMessage());
        detail.setType(new URI("employee/general"));
        detail.setTitle("General error");
        detail.setProperty("errorId", UUID.randomUUID().toString());
        return detail;
    }
}
