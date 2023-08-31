package co.phea.api.user;

import co.phea.api.user.web.CreateUserDto;
import co.phea.api.user.web.UpdateUserDto;
import co.phea.api.user.web.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById(Long id);

    UserDto findByUuid(String uuid);

    UserDto deleteByUuid(String uuid);

    UserDto createNewUser (CreateUserDto createUserDto);

    void createUserRole(Long id , List<Long> roleIds);

    UserDto updateAndExistByUuid(String uuid , UpdateUserDto updateUserDto);
}
