package com.company.contoller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserServiceInter userServiceInter;

    @GetMapping(value = "/users")
    public ResponseEntity getUsers(@RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "surname",required = false) String surname,
                                   @RequestParam(value = "nId",required = false) Integer nId) {

        List<User> userList = userServiceInter.getAll(name,surname,nId);
        List<UserDTO> userDTOList = new ArrayList<>();

        for (int i=0;i<userList.size();i++){
            User u = userList.get(i);
            userDTOList.add(new UserDTO(u));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOList));
        // return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
        // return ResponseEntity.ok(userDTOList);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id){
       User u = userServiceInter.getById(id);

       return ResponseEntity.ok(ResponseDTO.of("ok",new UserDTO(u)));
//        return ResponseEntity.ok(new UserDTO(u));

    }

    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser (@PathVariable(value = "id") Integer id) {
        User user = userServiceInter.getById(id);
        userServiceInter.removeUser(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseDTO.of("success",new UserDTO(user)));
    }

    @PostMapping(value = "/users")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());

        int userId = userServiceInter.addUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseDTO.of("success",null));
    }


    //Evvel @RestController olmuyanda methodun bashina requestbody yazirdilar ki anlashilsin bu string qaytarir view yox
//    @GetMapping(value = "/users/{id}")
//    public @RequestBody
//    ResponseEntity getUsers(@PathVariable("id") int id){
//        User u = userServiceInter.getById(id);
//        return ResponseEntity.ok(new UserDTO(u));
//    }
}
