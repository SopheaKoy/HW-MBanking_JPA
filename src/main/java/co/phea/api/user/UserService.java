package co.phea.api.user;

import co.phea.api.account.Account;
import co.phea.api.user.web.CreateUserDto;
import co.phea.api.user.web.UpdateUserDto;
import co.phea.api.user.web.UserDto;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    UserDto findById(Long id);

    UserDto findByUuid(String uuid);

    void deleteByUuid(String uuid);

    void disableByUuid(String uuid);

    UserDto createNewUser (CreateUserDto createUserDto);

    void createUserRole(Long id , List<Long> roleIds);

    UserDto updateAndExistByUuid(String uuid , UpdateUserDto updateUserDto);

    List<Account> findAccountByUserUuid(String uuid);


    Account  findAccountByUuidOfUser(String userUuid, String accountUuid);



}
