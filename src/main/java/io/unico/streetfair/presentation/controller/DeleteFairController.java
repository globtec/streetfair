package io.unico.streetfair.presentation.controller;

import io.unico.streetfair.domain.usecase.DeleteFairUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fairs")
public class DeleteFairController {
    private final DeleteFairUseCase deleteFairUseCase;

    @Autowired
    public DeleteFairController(DeleteFairUseCase deleteFairUseCase) {
        this.deleteFairUseCase = deleteFairUseCase;
    }

    @DeleteMapping("/{registry}")
    public ResponseEntity delete(@PathVariable("registry") String registry) {
        deleteFairUseCase.delete(registry);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
