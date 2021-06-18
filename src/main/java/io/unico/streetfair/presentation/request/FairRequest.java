package io.unico.streetfair.presentation.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FairRequest {

    private String id;

    private String lat;

    private String lon;

    private String setcems;

    private String weightingArea;

    private String districtCode;

    private String district;

    private String subprefectureCode;

    private String subprefecture;

    private String region5;

    private String region8;

    private String name;

    private String registry;

    private String address;

    private String number;

    private String neighborhood;

    private String landmark;

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
