package com.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.users.action.MessageSender;

@RequestMapping("/info")
@RestController
public class UserInfo {
	
	@Autowired
	private MessageSender messageSender;

	
	@GetMapping(path = "")
	public String getInfo() {
		return "Info" ;
	}
	
	@GetMapping(path = "/sendmessage")
	public Object sendMessage(@RequestParam String message) {
		
		return messageSender.sendMessage("q-user-exchange", "foo.bar.hello", message);
	}
}
