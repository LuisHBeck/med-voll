package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.user.UserData;
import med.voll.api.domain.dtos.user.UserListData;
import med.voll.api.domain.repositories.UserRepository;
import med.voll.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserData data, UriComponentsBuilder uriBuilder) {
        var user = userService.registerUser(data);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserListData(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> listUsers(@PageableDefault(size = 10, sort = {"id"})Pageable pagination) {
        var page = repository.findAll(pagination).map(UserListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        return ResponseEntity.ok(new UserListData(user));
    }
}
