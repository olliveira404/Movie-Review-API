package com.jadermarcelo.filmreview.controller;

import com.jadermarcelo.filmreview.model.Review;
import com.jadermarcelo.filmreview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;

    }

    @GetMapping
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/movie/{title}")
    public List<Review> getByMovieTitle(@PathVariable String title) {
        return repository.findByMovieTitle(title);

    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return repository.save(review);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review updateReview) {
        return repository.findById(id).map(review -> {
            review.setMovieTitle(updateReview.getMovieTitle());
            review.setReviewer(updateReview.getReviewer());
            review.setComment(updateReview.getComment());
            review.setRating(updateReview.getRating());
            return repository.save(review);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {

        repository.deleteById(id);
        
    }
}
