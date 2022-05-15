package com.example.dsmovie.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_score")
public class Score {
    
    @EmbeddedId
    private ScorePK id = new ScorePK();    
    private Double value;

    public Score() {
    }

    public Score(ScorePK id, Double value) {
        this.id = id;
        this.value = value;
    }

    public void setUser(User user){
        this.id.setUser(user);
    }

    public void setMovie(Movie movie){
        this.id.setMovie(movie);
    }

    public ScorePK getId() {
        return this.id;
    }

    public void setId(ScorePK id) {
        this.id = id;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Score id(ScorePK id) {
        setId(id);
        return this;
    }

    public Score value(Double value) {
        setValue(value);
        return this;
    }
}
