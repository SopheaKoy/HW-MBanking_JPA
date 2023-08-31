package co.phea.api.account;

import co.phea.api.accounttype.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Entity
@Builder
@Relation(collectionRelation = "accounts" , itemRelation = "account")
public class Account extends RepresentationModel<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String pin;

    @Column(name = "act_name")
    private String actName;

    @Column(name = "act_no")
    private String actNo;

    @Column(name="transfer_limit")
    private BigDecimal transferLimit;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;


}
