package org.pablos.backend.domain.exception.handler;

import org.pablos.backend.domain.exception.SearchLengthException;
import org.pablos.backend.domain.exception.SizeException;
import org.pablos.backend.domain.exception.SortingTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RESTControllerGetPagesMethodExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SortingTypeException.class)
    protected ResponseEntity<CustomException> handleSortingTypeException() {
        return new ResponseEntity<>(new CustomException("Ошибка при указании типа сортировки"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SizeException.class)
    protected ResponseEntity<CustomException> handleSizeException() {
        return new ResponseEntity<>(new CustomException("Недопустимое количество записей на странице"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SearchLengthException.class)
    protected ResponseEntity<CustomException> handleSearchLengthException() {
        return new ResponseEntity<>(new CustomException("Недопустимая длина поискового запроса"), HttpStatus.BAD_REQUEST);
    }

        private record CustomException(String message) {
    }
}