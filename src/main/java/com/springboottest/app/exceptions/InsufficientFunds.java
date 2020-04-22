package com.springboottest.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Insufficient funds on the deposit")
public class InsufficientFunds extends RuntimeException {
}
