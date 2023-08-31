package co.phea.api.accounttype.web;

import co.phea.api.accounttype.AccountType;
import co.phea.api.accounttype.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {

    private final AccountTypeRepository accountTypeRepository;
    @GetMapping
    public List<AccountTypeDto> findAccountType(){
        return accountTypeRepository.findAllAccountType();
    }

    @GetMapping("/{id}")
    public AccountTypeDto findAccountType(@PathVariable Long id){

        return accountTypeRepository.findAccountTypeById(id);

    }


}
