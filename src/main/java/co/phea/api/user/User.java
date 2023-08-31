package co.phea.api.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String name;

    @Column(length = 10)
    private String gender;

    @Column(name = "phone_number" ,length = 30)
    private String phoneNumber;

    private String email;

    private String password;

    private BigDecimal balance;

    @Column(name= "student_card_no")
    private String studentCardNo;

    @Column(name = "one_signal_id")
    private String oneSignalId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_student")
    private boolean isStudent;

    @Column(name = "is_verified")
    private boolean  isVerified;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccounts;


}
