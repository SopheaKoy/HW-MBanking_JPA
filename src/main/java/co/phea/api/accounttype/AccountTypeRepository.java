package co.phea.api.accounttype;

import co.phea.api.accounttype.web.AccountTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountTypeRepository extends JpaRepository<AccountType ,Long> {

    @Query("select new co.phea.api.accounttype.web.AccountTypeDto(a.name) from AccountType a ")
    List<AccountTypeDto> findAllAccountType();


    @Query("select new co.phea.api.accounttype.web.AccountTypeDto(a.name) from AccountType a where a.id = :id")
    AccountTypeDto findAccountTypeById(@Param("id") Long id);



}
