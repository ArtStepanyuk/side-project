package com.softserveinc.charity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Category is mandatory field.")
public class CategoryNotPresentException extends RuntimeException {
}
