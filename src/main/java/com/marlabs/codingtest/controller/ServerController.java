package com.marlabs.codingtest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.marlabs.codingtest.model.MsgRequest;
import com.marlabs.codingtest.service.ServiceReceiver;


@RestController
@RequestMapping(path="/server")
public class ServerController {
	@Autowired
	ServiceReceiver service;
	
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	public String getMsg() {
		return "Server";
	}
	
	@PostMapping(path= "/msg", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addEmployee(@RequestBody MsgRequest request) 
    {
		try {
			String msg = request.getMessage();
			int id = request.getId();
			System.out.println("Message: " + msg);

			if (id == 2) {// verify the answer
				try {
					boolean response = service.vaidateInputRequest(msg);
					String respMsg = "";
					if (response) {
						respMsg = "That's great";
						return new ResponseEntity<String>(respMsg,HttpStatus.OK);
					} else {
						respMsg = "That's wrong. Please try again.";
						return new ResponseEntity<String>(respMsg,HttpStatus.BAD_REQUEST);
					}
				}catch(Exception ex) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			} else if (id == 1) { // question
				String resp=service.getQuestion();
				System.out.println(resp);
				return new ResponseEntity<String>(resp,HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

    }
}
