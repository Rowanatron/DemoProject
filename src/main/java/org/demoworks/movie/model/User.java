package org.demoworks.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demoworks.movie.model.enums.Role;
import javax.persistence.*;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Enum<Role> role;
    @OneToMany (mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Rating> ratings;
    @OneToMany (mappedBy = "reviewer", fetch = FetchType.LAZY)
    private Collection<Review> reviews;

    public boolean isCritic (){
        return this.getRole() == Role.CRITIC;
    }

}
