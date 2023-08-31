package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    List<AccountDto> dtoToListModel (List<Account> model);

    AccountDto dtoToModel (Account account);

}
