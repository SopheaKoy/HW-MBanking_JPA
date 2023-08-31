package co.phea.api.transaction;

import co.phea.api.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = " receiver_act_id")
    private Account receiverActAd;

    @ManyToOne
    @JoinColumn(name = "sender_act_id")
    private Account senderActId;

    private String remark;

    @Column(name = "student_card_no")
    private String studentCardNo;

    @Column(name = "transaction_at")
    private LocalDateTime transactionAt;

    private boolean isPayment;

    private BigDecimal amount;


}
