package com.example.moviecommu.controller;

import com.example.moviecommu.dto.ReviewDto;
import com.example.moviecommu.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/movie/{id}/write")
    public ResponseEntity<Void> addReview(@RequestBody ReviewDto reviewDto, @PathVariable Long id) {
        reviewService.writeReview(reviewDto, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/movie/{id}/update")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Long id) {
        reviewService.updateReview(reviewDto, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/movie/{movieId}/delete")
    public void deleteReview(@RequestParam Long userId, @PathVariable Long movieId) {
        System.out.println(movieId);
        reviewService.deleteReview(userId, movieId);
    }
}
