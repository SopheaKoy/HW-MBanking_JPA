package co.phea.api.account.web;

import co.phea.api.account.AccountMapStruct;
import co.phea.api.account.AccountRepository;
import co.phea.api.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountMapStruct accountMapStruct;

    @GetMapping
    public CollectionModel<?> findAccount(){

        var listAccount = accountService.findAccount();

        return CollectionModel.of(listAccount);

    }

    @GetMapping("/{uuid}")
    public EntityModel<AccountDto> findAccountByUuid(@PathVariable String uuid){

        return accountService.findAccountByUuid(uuid);

    }

    @PostMapping
    public void createNewAccount(@RequestBody CreateAccountDto createAccountDto){

        accountService.createNewAccount(createAccountDto);

    }

    @PostMapping ("/{uuid}/rename")
    public void renameAccountByUuid(@PathVariable String uuid , @RequestBody RenameAccountDto renameAccountDto){

        accountService.renameAndExistByUuid(uuid, renameAccountDto);

    }

    @PostMapping ("/{uuid}/transfer-limit")
    public void changeTransferLimit(@PathVariable String uuid , @RequestBody ChangeTransferAccountDto changeTransferAccountDto){

        accountService.changeTransferLimit(uuid, changeTransferAccountDto);

    }


    @PutMapping("/{uuid}/closed")
    public void disableAccountByUuid(@PathVariable String uuid, @RequestBody IsDeletedDto isDeletedDto){
        accountService.disableAccountByUuid(uuid, isDeletedDto);
    }



}
