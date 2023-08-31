package co.phea.api.account.web;

import co.phea.api.account.Account;
import co.phea.api.account.AccountRepository;
import co.phea.api.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping
    public CollectionModel<?> findAccount(){

        var listAccount = accountService.findAccount();

        return CollectionModel.of(listAccount);

    }

    @GetMapping("/{uuid}")
    public EntityModel<AccountDto> findAccountByUuid(@PathVariable String uuid){

        return accountService.findAccountByUuid(uuid);

    }





}
