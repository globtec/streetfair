package io.unico.streetfair.presentation.response;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.presentation.controller.RetrieveFairController;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class FairResponse extends RepresentationModel<FairResponse> {

    private final String id;

    private final String lat;

    private final String lon;

    private final String setcems;

    private final String weightingArea;

    private final String districtCode;

    private final String district;

    private final String subprefectureCode;

    private final String subprefecture;

    private final String region5;

    private final String region8;

    private final String name;

    private final String registry;

    private final String address;

    private final String number;

    private final String neighborhood;

    private final String landmark;

    public FairResponse(Fair fair) {
        id = fair.getId();
        lat = fair.getLat();
        lon = fair.getLon();
        setcems = fair.getSetcems();
        weightingArea = fair.getWeightingArea();
        districtCode = fair.getDistrictCode();
        district = fair.getDistrict();
        subprefectureCode = fair.getSubprefectureCode();
        subprefecture = fair.getSubprefecture();
        region5 = fair.getRegion5();
        region8 = fair.getRegion8();
        name = fair.getName();
        registry = fair.getRegistry();
        address = fair.getAddress();
        number = fair.getNumber();
        neighborhood = fair.getNeighborhood();
        landmark = fair.getLandmark();

        add(linkTo(RetrieveFairController.class).slash(registry).withSelfRel());
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getSetcems() {
        return setcems;
    }

    public String getWeightingArea() {
        return weightingArea;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getSubprefectureCode() {
        return subprefectureCode;
    }

    public String getSubprefecture() {
        return subprefecture;
    }

    public String getRegion5() {
        return region5;
    }

    public String getRegion8() {
        return region8;
    }

    public String getName() {
        return name;
    }

    public String getRegistry() {
        return registry;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getLandmark() {
        return landmark;
    }

}