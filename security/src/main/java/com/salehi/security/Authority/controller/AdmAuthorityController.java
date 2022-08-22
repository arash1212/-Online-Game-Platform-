package com.salehi.security.Authority.controller;

import com.salehi.security.Authority.dto.AuthorityInput;
import com.salehi.security.Authority.dto.AuthorityOutput;
import com.salehi.security.Authority.service.AuthorityService;
import com.salehi.utility.constant.PathVariableConstant;
import com.salehi.utility.constant.RestControllerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = RestControllerConstant.ADM + "/authorities")
public class AdmAuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorityOutput> user(@PathVariable(name = PathVariableConstant.ID) Long id) {
        return ResponseEntity.ok(this.authorityService.findById(id));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<AuthorityOutput>> users() {
        return ResponseEntity.ok(this.authorityService.findAll());
    }

    @PostMapping(path = "")
    public ResponseEntity<Long> create(@RequestBody AuthorityInput input) {
        return ResponseEntity.ok(this.authorityService.save(input));
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable(name = PathVariableConstant.ID) Long id, @RequestBody AuthorityInput input) {
        this.authorityService.update(id, input);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) Long id) {
        this.authorityService.delete(id);
    }

}
