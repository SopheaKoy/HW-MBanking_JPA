package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import co.phea.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account , Long>  {


    Optional<Account> findByUuid(String uuid);

    List<Account> findByIsDeletedFalse();

    boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE Account a SET a.isDeleted = ?1 WHERE a.uuid =?2")
    void disableAccountByUuid(Boolean isDeleted , String uuid);

    @Modifying
    @Query("UPDATE Account a set a.actName = ?1 where a.uuid = ?2 ")
    void renameAccountByUuid(String uuid , String actName);
    @Modifying
    @Query("UPDATE Account a set a.transferLimit = ?1 where a.uuid = ?2 ")
    void changeTransferAccountByUuid(String uuid , BigDecimal transferLimit);



}
