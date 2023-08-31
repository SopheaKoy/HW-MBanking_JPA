package co.phea.api.account;

import co.phea.api.account.web.AccountDto;
import co.phea.api.account.web.AccountRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;


@Component
public class AccountModelAssembler extends RepresentationModelAssemblerSupport<Account, EntityModel<AccountDto>> {

    @Autowired
    private  AccountMapStruct accountMapStruct;

    public void setAccountMapStruct(AccountMapStruct accountMapStruct) {
        this.accountMapStruct = accountMapStruct;
    }

    @SuppressWarnings("unchecked")
    public AccountModelAssembler() {
        super(AccountRestController.class,(Class<EntityModel<AccountDto>>) (Class<?>)EntityModel.class);
    }

    @Override
    public EntityModel<AccountDto> toModel(Account entity) {

        AccountDto accountDto = accountMapStruct.dtoToModel(entity);

        Link self = linkTo(methodOn(AccountRestController.class).findAccount()).withSelfRel();
//        Link linkUuid = linkTo(methodOn(AccountRestController.class).findAccountByUuid(entity.getUuid()))
//                .withRel(IanaLinkRelations.RELATED);


        return EntityModel.of(accountDto , self);
    }

    @Override
    public CollectionModel<EntityModel<AccountDto>> toCollectionModel(Iterable<? extends Account> entities) {
        return super.toCollectionModel(entities);
    }
}
