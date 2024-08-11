package com.bookstore.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity

@Table(name="tb_book")
public class BookModel implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name="publisher_id")
    private PublisherModel publisher;

    @ManyToMany
    @JoinTable(
            name = "tb_book_author",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReviewModel review;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }
}
