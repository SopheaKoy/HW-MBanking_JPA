package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountModelAssembler accountModelAssembler;

    @Override
    public CollectionModel<?> findAccount() {

        var listAccount = accountRepository.findAll();

        return accountModelAssembler.toCollectionModel(listAccount);
    }

    @Override
    public EntityModel<AccountDto> findAccountByUuid(String uuid) {

        AccountDto findAccountUuid = accountRepository.findByUuid(uuid);

        return EntityModel.of(findAccountUuid);
    }




}
