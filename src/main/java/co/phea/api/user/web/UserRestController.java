package co.phea.api.user.web;

import co.phea.api.account.Account;
import co.phea.api.user.User;
import co.phea.api.user.UserMapStruct;
import co.phea.api.user.UserRepository;
import co.phea.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapStruct userMapStruct;

    @GetMapping
    public CollectionModel<?> findUser(){

        List<User> user = userRepository.findByIsDeletedFalse();

        return CollectionModel.of(userMapStruct.dtoToUserList(user));
    }

    @GetMapping("/{uuid}")
    public UserDto findUserByUuid(@PathVariable String uuid){

        return userService.findByUuid(uuid);

    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto){

        return userService.createNewUser(createUserDto);
    }

    @PutMapping("/{uuid}")

    public UserDto updateUserByUuid(@PathVariable String uuid , @RequestBody UpdateUserDto updateUserDto){

        return userService.updateAndExistByUuid(uuid, updateUserDto);
    }

    @DeleteMapping("/{uuid}")
    public void deleteUserByUuid(@PathVariable String uuid){

         userService.deleteByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    public void disableByUuid(@PathVariable String uuid){

        userService.disableByUuid(uuid);

    }

    @GetMapping("/{uuid}/accounts")
    public List<Account> findAllAccountByUserUuid(@PathVariable String uuid){

        return userService.findAccountByUuid(uuid);
    }
}
