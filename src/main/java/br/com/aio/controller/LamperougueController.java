package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LamperougueController {
	
	@RequestMapping(value = "/lamperougue", method = POST)
	public ResponseEntity<String> teste(){
		return new ResponseEntity<String>("deu certo", HttpStatus.ACCEPTED);
	}

}
