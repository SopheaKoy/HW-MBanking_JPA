package co.phea.api.user;

import co.phea.api.user.web.CreateUserDto;
import co.phea.api.user.web.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    List<UserDto> dtoToUserList (List<User> userList);

    UserDto dtoToUser (User user);

    CreateUserDto createUserDtoToUser(User user);

    User createUserToDto(CreateUserDto createUserDto);
}
