package com.hello2pal.socialMediaApp.errorhandler;

import com.hello2pal.socialMediaApp.dto.ApiError;
import com.hello2pal.socialMediaApp.dto.ErrorDetails;
import com.hello2pal.socialMediaApp.exception.SocialAppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        Map<String, String> metas = new HashMap<>();
        metas.put("UUID", (String) request.getAttribute("refId", 0));
        ErrorDetails error = new ErrorDetails(402, "Internal Server Error ");
        final ApiError apiError = ApiError.builder().metaData(metas).error(error).build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SocialAppException.class})
    public ResponseEntity<Object> handleAll(final SocialAppException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        ErrorDetails error = ErrorDetails.builder().code(ex.getErrorCode()).message(ex.getMessage()).build();
        Map<String, String> metas = new HashMap<>();
        metas.put("UUID", (String) request.getAttribute("refId", 0));
        final ApiError apiError = ApiError.builder().metaData(metas).error(error).build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    }

}
