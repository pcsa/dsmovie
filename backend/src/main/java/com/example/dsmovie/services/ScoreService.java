package com.example.dsmovie.services;

import com.example.dsmovie.dto.MovieDTO;
import com.example.dsmovie.dto.ScoreDTO;
import com.example.dsmovie.entities.Movie;
import com.example.dsmovie.entities.Score;
import com.example.dsmovie.entities.User;
import com.example.dsmovie.repositories.MovieRepository;
import com.example.dsmovie.repositories.ScoreRepository;
import com.example.dsmovie.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;
    
    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO) {
        User user = userRepository.findByEmail(scoreDTO.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());
        score = scoreRepository.saveAndFlush(score);

        double sum = 0.;
        for (Score s : movie.getScores()) {
            sum += s.getValue();
        }
        double avg = sum / movie.getScores().size();
        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}
