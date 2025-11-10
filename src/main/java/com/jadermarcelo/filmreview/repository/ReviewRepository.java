package com.jadermarcelo.filmreview.repository;

import com.jadermarcelo.filmreview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieTitle(String movieTitle);
}
