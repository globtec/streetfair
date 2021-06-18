package io.unico.streetfair.presentation.controller;

import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.domain.usecase.RetrieveFairUseCase;
import io.unico.streetfair.presentation.response.FairCollectionResponse;
import io.unico.streetfair.presentation.response.FairResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fairs")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class RetrieveFairController {

    private final RetrieveFairUseCase retrieveFairUseCase;

    @Autowired
    public RetrieveFairController(RetrieveFairUseCase retrieveFairUseCase) {
        this.retrieveFairUseCase = retrieveFairUseCase;
    }

    @GetMapping(produces = {"application/json"})
    public HttpEntity<CollectionModel<?>> retrieveBy(@RequestParam(required = false) String district,
                                                     @RequestParam(required = false) String region5,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String neighborhood) throws FairNotFoundException {

        var fairs = retrieveFairUseCase.findBy(district, region5, name, neighborhood);
        var response = new FairCollectionResponse(fairs, district, region5, name, neighborhood);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{registry}", produces = {"application/json"})
    public HttpEntity<RepresentationModel<?>> retrieveByRegistry(@PathVariable("registry") String registry) throws FairNotFoundException {
        var fair = retrieveFairUseCase.find(registry);
        var response = new FairResponse(fair);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
