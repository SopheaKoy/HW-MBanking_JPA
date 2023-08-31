package co.phea.init;


import co.phea.api.account.Account;
import co.phea.api.account.AccountRepository;
import co.phea.api.accounttype.AccountType;
import co.phea.api.accounttype.AccountTypeRepository;
import co.phea.api.auth.Role;
import co.phea.api.auth.RoleRepository;
import co.phea.api.user.User;
import co.phea.api.user.UserRepository;
import co.phea.api.user.UserRole;
import co.phea.api.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DataInit {


    @PostConstruct
    void initAccountType(){

    }

}
