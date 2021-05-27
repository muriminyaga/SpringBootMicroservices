package com.novapile.userservice.controller;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;

import com.novapile.userservice.entity.User;
import com.novapile.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
   private UserService userService;

   @PostMapping("/")
   public User saveUser(@RequestBody User user){
       return userService.saveUser(user);
   }

   @GetMapping("/")
    public List fetchUsers(){
        return userService.fetchUsers();
    }

   @GetMapping("/{id}")
   public User findUserById(@PathVariable("id") Long userId){
       return userService.findUserById(userId);
   }

    @GetMapping("/{id}/payments")
    public List findUserPayments(@PathVariable("id") Long userId){
        return userService.findUserPayments(userId);
    }

    @GetMapping("/count")
    public int findUserById(){
        return userService.findTotalUsers();
    }

   /*testing rest responseEntity*/
    @GetMapping("/multiValue")
    public ResponseEntity<String> multiValue(
            @RequestHeader MultiValueMap<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping("/getBaseUrl")
    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
        InetSocketAddress host = headers.getHost();
        String url = "http://" + host.getHostName() + ":" + host.getPort();
        return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/age")
    ResponseEntity<String> age(
            @RequestParam("yearOfBirth") int yearOfBirth) {

        if (yearOfBirth>2021) {
            return new ResponseEntity<>(
                    "Year of birth cannot be in the future",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                "Your YR OF birth is" +  (yearOfBirth),
                HttpStatus.OK);
    }
    /*testing rest responseEntity*/
}
