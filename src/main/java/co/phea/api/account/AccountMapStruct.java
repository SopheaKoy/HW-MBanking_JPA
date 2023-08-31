package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import co.phea.api.account.web.ChangeTransferAccountDto;
import co.phea.api.account.web.CreateAccountDto;
import co.phea.api.account.web.RenameAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    List<AccountDto> dtoToListModel (List<Account> model);

    AccountDto dtoToModel (Account account);

    Account createAccountToDto(CreateAccountDto createAccountDto);

    Account renameAccountToDto(RenameAccountDto renameAccountDto);

    Account accountToDtoTransfer(ChangeTransferAccountDto transferAccountDto);



}
