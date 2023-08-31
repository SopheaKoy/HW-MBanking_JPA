package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountRepository extends JpaRepository<Account , Long>  {


    AccountDto findByUuid(String uuid);

    boolean existsByUuid(String uuid);



}
