package com.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.dto.Request;
import com.sample.model.dto.Response;
import com.sample.service.ApplicationService;

import reactor.core.publisher.Mono;

@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService service;

	@PostMapping(path = "/greet", consumes = "*/*", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Response> greet(@Valid @RequestBody Request request) {

		service.greet(request);
		
		return Mono.just(new Response("SUCCESS"));
	}

}
