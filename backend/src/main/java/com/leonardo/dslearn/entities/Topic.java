package com.leonardo.dslearn.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_topic")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToMany
    @JoinTable(name = "tb_topic_likes",
        joinColumns = @JoinColumn(name = "topic_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply answer;

    @OneToMany(mappedBy = "topic")
    private List<Reply> replies = new ArrayList<>();

    public Topic(){}

    public Topic(Long id, String title, String body, Instant moment) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Reply getAnswer() {
        return answer;
    }

    public void setAnswer(Reply answer) {
        this.answer = answer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Set<User> getLikes(){
        return likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
