package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface AccountService {

    CollectionModel<?> findAccount();

    EntityModel<AccountDto> findAccountByUuid(String uuid);


}
