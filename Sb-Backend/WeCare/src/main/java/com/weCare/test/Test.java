package com.weCare.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tests")
public class Test {

	@PostMapping()
	public ResponseEntity<TestEntity> medicationTest(@Valid @RequestBody TestEntity entity) {
		return new ResponseEntity<TestEntity>(entity, HttpStatus.ACCEPTED);
	}

}
