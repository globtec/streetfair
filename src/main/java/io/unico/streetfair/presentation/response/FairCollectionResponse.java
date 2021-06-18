package io.unico.streetfair.presentation.response;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.presentation.controller.RetrieveFairController;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FairCollectionResponse extends CollectionModel {

    private List<FairResponse> fairs;

    public FairCollectionResponse(List<Fair> fairs, String district, String region5, String name, String neighborhood) throws FairNotFoundException {
        this.fairs = fairs.stream()
                .map(FairResponse::new)
                .collect(Collectors.toList());

        this.add(linkTo(
                methodOn(RetrieveFairController.class)
                .retrieveBy(district, region5, name, neighborhood))
                .withSelfRel());
    }

    public List<FairResponse> getFairs() {
        return fairs;
    }
}
