package com.salehi.user.model.user.controller;

import com.salehi.user.model.user.dto.UsersInput;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.service.UsersService;
import com.salehi.utility.constant.PathVariableConstant;
import com.salehi.utility.constant.RestControllerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/users")
public class PubUsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsersOutput> user(@PathVariable(name = PathVariableConstant.ID) Long id) {
        return ResponseEntity.ok(this.usersService.findById(id));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<UsersOutput>> users() {
        return ResponseEntity.ok(this.usersService.findAll());
    }

    @PostMapping(path = "")
    public ResponseEntity<Long> create(@Valid @RequestBody UsersInput input) {
        return ResponseEntity.ok(this.usersService.save(input));
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable(name = PathVariableConstant.ID) Long id,@Valid @RequestBody UsersInput input) {
        this.usersService.update(id, input);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) Long id) {
        this.usersService.delete(id);
    }

}
