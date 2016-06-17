package rest.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.backend.repository.jdbc.UserRepository;
import rest.backend.util.StringUtility;

@RestController
@RequestMapping("/login")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/dologin", method= RequestMethod.GET, produces="application/json")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        String response = "";
    	if(userRepository.checkCredentials(username,password)){
    		response = StringUtility.constructJSON("login", true);
    	}else{
    		response = StringUtility.constructJSON("login", false,"Usuario o contraseña incorrecta");
    	}
        return response;
    }

}
