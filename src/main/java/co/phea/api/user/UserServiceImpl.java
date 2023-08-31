package co.phea.api.user;

import co.phea.api.account.Account;
import co.phea.api.account.AccountRepository;
import co.phea.api.auth.Role;
import co.phea.api.user.web.CreateUserDto;
import co.phea.api.user.web.UpdateUserDto;
import co.phea.api.user.web.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapStruct userMapStruct;
    private final UserRoleRepository userRoleRepository;
    private final AccountRepository accountRepository;
    private final UserAccountRepository userAccountRepository;


    @Override
    public UserDto findById(Long id) {

        User user = userRepository.findById(id).orElseThrow();

        return userMapStruct.dtoToUser(user);

    }

    @Override
    public UserDto findByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                        String.format("User UUID : %s is not found. " + uuid))
        );
        return userMapStruct.dtoToUser(user);
    }

    @Override
    public void deleteByUuid(String uuid) {

        if(userRepository.existsByUuid(uuid)){

            userRepository.deleteByUuid(uuid);
            return;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND ,
                String.format("User UUID : %s is not found. " + uuid));

    }

    @Override
    public void disableByUuid(String uuid) {

        if(userRepository.existsByUuid(uuid)) {

            userRepository.updateIsDeleteByUuid(true , uuid);

            return;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND ,
                String.format("User UUID is not found. " + uuid));

    }

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {

        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .name(createUserDto.name())
                .gender(createUserDto.gender())
                .email(createUserDto.email())
                .phoneNumber(createUserDto.phoneNumber())
                .isStudent(false)
                .isDeleted(false)
                .isVerified(true)
                .build();

        user = userRepository.save(user);

        createUserRole(user.getId(), createUserDto.roleIds());

        return userMapStruct.dtoToUser(user);
    }

    @Override
    public void createUserRole(Long userId, List<Long> roleIds) {

        roleIds.forEach(id ->{

            UserRole userRole = UserRole.builder()
                    .role(Role.builder().id(id).build())
                    .user(User.builder().id(userId).build())
                    .build();

            userRoleRepository.save(userRole);

        });

    }

    @Override
    public UserDto updateAndExistByUuid(String uuid, UpdateUserDto updateUserDto) {

        User updateUser = userRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                        String.format("User UUID : %s is not found. " + uuid))
        );

        updateUser.setName(updateUserDto.name());
        updateUser.setGender(updateUserDto.gender());
        updateUser.setPhoneNumber(updateUserDto.phoneNumber());
        updateUser = userRepository.save(updateUser);

        return userMapStruct.dtoToUser(updateUser);
    }

//    @Override
//    public User findAllAccountByUserUuid(String uuid , List<Account> accountList) {
//
//        boolean isExist = userRepository.existsByUuid(uuid);
//
//        if (isExist){
//
//            User user = userRepository.findByUuid(uuid).orElseThrow(
//                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
//                            String.format("User UUID : %s is not found. " + uuid)
//            ));
//
//        }
//
//        return null;
//    }

    @Override
    public List<Account> findAccountByUuid(String uuid) {

       List<UserAccount> userAccounts = userAccountRepository.findByUuid(uuid);
       List<Account> accounts = userAccounts.stream().map(
               UserAccount::getAccount)
               .collect(Collectors.toList());

        return accounts;
    }


}
