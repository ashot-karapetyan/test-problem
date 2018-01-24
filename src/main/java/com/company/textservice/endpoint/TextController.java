package com.company.textservice.endpoint;

import com.company.textservice.service.QuoteExtractionResult;
import com.company.textservice.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
@RestController
public class TextController {

	
	private static final Logger logger = LogManager.getLogger(TextController.class);

	@Autowired
	private TextService textService;

	@PostMapping("/")
	public QuoteExtractionResult getQuotes(@RequestBody String data) {
		return this.textService.findQuotes(data);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		logger.error(ex::getMessage, ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		logger.error(ex::getMessage, ex);
		return new ResponseEntity<>("Something gone wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		logger.error(ex::getMessage, ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	public ResponseEntity<String> handleUnsatisfiedServletRequestParameterException(
			UnsatisfiedServletRequestParameterException ex) {
		logger.error(ex::getMessage, ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		logger.error(ex::getMessage, ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

}
