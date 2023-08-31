package co.phea.api.auth;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable (
            name = "roles_authorities",
            joinColumns = @JoinColumn(name = "roles_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id" , referencedColumnName = "id")
    )
    private List<Authority> authorities;

}
