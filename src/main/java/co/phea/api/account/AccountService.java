package co.phea.api.account;

import co.phea.api.account.web.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface AccountService {

    CollectionModel<?> findAccount();

    EntityModel<AccountDto> findAccountByUuid(String uuid);

    EntityModel<AccountDto> createNewAccount(CreateAccountDto createAccountDto);

    EntityModel<?> renameAndExistByUuid(String uuid, RenameAccountDto renameAccountDto);

    EntityModel<?> changeTransferLimit(String uuid, ChangeTransferAccountDto transferAccountDto);

    void disableAccountByUuid(String uuid , IsDeletedDto isDeletedDto);


}
