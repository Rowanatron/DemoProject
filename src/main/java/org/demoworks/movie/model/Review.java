package org.demoworks.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "film_id", nullable = false)
    private Film film;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User reviewer;
    private String review;
    private LocalDate submitted;
    private LocalDateTime lastEdited;

    public Review(Film film, User reviewer, String review, LocalDate submitted, LocalDateTime lastEdited) {
        this.film = film;
        this.reviewer = reviewer;
        this.review = review;
        this.submitted = submitted;
        this.lastEdited = lastEdited;

    }

}
