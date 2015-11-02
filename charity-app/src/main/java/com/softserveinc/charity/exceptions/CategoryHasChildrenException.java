package com.softserveinc.charity.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Сategory in posted Need/Offer should be without children.")
public class CategoryHasChildrenException extends RuntimeException{
}
