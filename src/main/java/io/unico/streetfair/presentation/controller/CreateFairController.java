package io.unico.streetfair.presentation.controller;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.usecase.CreateFairUseCase;
import io.unico.streetfair.presentation.request.FairRequest;
import io.unico.streetfair.presentation.response.FairResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fairs")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class CreateFairController {

    private final CreateFairUseCase createFairUseCase;

    @Autowired
    public CreateFairController(CreateFairUseCase createFairUseCase) {
        this.createFairUseCase = createFairUseCase;
    }

    @PostMapping(produces = {"application/json"})
    public HttpEntity<FairResponse> create(@RequestBody FairRequest request) throws Exception {
        var fair = createFairUseCase.create(new Fair.Builder(request.getRegistry())
                .id(request.getId())
                .lat(request.getLat())
                .lon(request.getLon())
                .setcems(request.getSetcems())
                .weightingArea(request.getWeightingArea())
                .districtCode(request.getDistrictCode())
                .district(request.getDistrict())
                .subprefectureCode(request.getSubprefectureCode())
                .subprefecture(request.getSubprefecture())
                .region5(request.getRegion5())
                .region8(request.getRegion8())
                .name(request.getName())
                .address(request.getAddress())
                .number(request.getNumber())
                .neighborhood(request.getNeighborhood())
                .landmark(request.getLandmark())
                .build());

        var response = new FairResponse(fair);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}