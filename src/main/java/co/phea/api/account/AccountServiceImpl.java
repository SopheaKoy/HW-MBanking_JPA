package co.phea.api.account;

import co.phea.api.account.web.*;
import co.phea.api.user.User;
import co.phea.api.user.UserAccount;
import co.phea.api.user.UserAccountRepository;
import co.phea.api.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountModelAssembler accountModelAssembler;
    private final AccountMapStruct accountMapStruct;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    @Override
    public CollectionModel<?> findAccount() {

        var listAccount = accountRepository.findByIsDeletedFalse();

        return accountModelAssembler.toCollectionModel(listAccount);
    }

    @Override
    public EntityModel<AccountDto> findAccountByUuid(String uuid) {

        Account findAccountUuid = accountRepository.findByUuid(uuid).orElseThrow();

        return EntityModel.of(accountMapStruct.dtoToModel(findAccountUuid));
    }

    @Override
    public EntityModel<AccountDto> createNewAccount(CreateAccountDto createAccountDto) {


       Account newAccount = accountMapStruct.createAccountToDto(createAccountDto);

       newAccount.setUuid(UUID.randomUUID().toString());

       newAccount.setActName(createAccountDto.actName());

       newAccount.setActNo(createAccountDto.actNo());

       newAccount.setTransferLimit(createAccountDto.transferLimit());

        newAccount = accountRepository.save(newAccount);


        User user = User.builder()
                .id(newAccount.getId())
                .build();

        user = userRepository.save(user);

        UserAccount userAccount = UserAccount.builder()

                .user(user)

                .account(newAccount)

                .isDisables(false)

                .createAt(LocalDateTime.now())

                .updateAt(LocalDateTime.now())

                .build();


        userAccountRepository.save(userAccount);

        return accountModelAssembler.toModel(newAccount);
    }

    @Transactional
    @Override
    public EntityModel<?> renameAndExistByUuid(String uuid, RenameAccountDto renameAccountDto) {

        boolean isExist = accountRepository.existsByUuid(uuid);

        if (isExist) {
            // Find Uuid to update
            Account account = accountRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                            String.format("User UUID : %s is not found. " + uuid))
            );


            account.setActName(renameAccountDto.actName());


            account = accountRepository.save(account);

            return EntityModel.of(account);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User UUID %s is not found.", uuid));
    }

    @Override
    public EntityModel<?> changeTransferLimit(String uuid, ChangeTransferAccountDto transferAccountDto) {

        boolean isExist = accountRepository.existsByUuid(uuid);

        if(isExist){

            Account account = accountRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                            String.format("User UUID : %s is not found. " + uuid))
            );


            account.setActName(transferAccountDto.transferLimit().toString());

            account = accountRepository.save(account);

            return EntityModel.of(account);

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User UUID %s is not found.", uuid));
    }

    @Transactional
    @Override
    public void disableAccountByUuid(String uuid, IsDeletedDto isDeletedDto) {

        if(accountRepository.existsByUuid(uuid)) {

            accountRepository.disableAccountByUuid(isDeletedDto.isDeleted(), uuid);

            return;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND ,
                String.format("User UUID is not found. " + uuid));
    }

}
