package org.demoworks.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rating", uniqueConstraints = @UniqueConstraint(columnNames = {"film_id", "user_id"}))
@Data
@NoArgsConstructor
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Integer id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private double rating;


    public Rating(Film film, User user, double rating){
        this.film = film;
        this.user = user;
        this.rating = rating;
    }

}
