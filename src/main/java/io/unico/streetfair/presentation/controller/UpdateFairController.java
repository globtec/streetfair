package io.unico.streetfair.presentation.controller;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.usecase.UpdateFairUseCase;
import io.unico.streetfair.presentation.request.FairRequest;
import io.unico.streetfair.presentation.response.FairResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fairs")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class UpdateFairController {

    private final UpdateFairUseCase updateFairUseCase;

    @Autowired
    public UpdateFairController(UpdateFairUseCase updateFairUseCase) {
        this.updateFairUseCase = updateFairUseCase;
    }

    @PutMapping(value = "/{registry}", produces = {"application/json"})
    public HttpEntity<FairResponse> update(@RequestBody FairRequest request,
                                           @PathVariable("registry") String registry) throws Exception {

        var fair = updateFairUseCase.update(new Fair.Builder(registry)
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
