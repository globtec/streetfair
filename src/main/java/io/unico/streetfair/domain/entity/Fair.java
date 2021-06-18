package io.unico.streetfair.domain.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fair extends RepresentationModel<Fair> {

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

    @Id
    private String registry;

    private String address;

    private String number;

    private String neighborhood;

    private String landmark;

    public static class Builder {

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

        public Builder(String registry) {
            this.registry = registry;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder lat(String lat) {
            this.lat = lat;
            return this;
        }

        public Builder lon(String lon) {
            this.lon = lon;
            return this;
        }

        public Builder setcems(String setcems) {
            this.setcems = setcems;
            return this;
        }

        public Builder weightingArea(String weightingArea) {
            this.weightingArea = weightingArea;
            return this;
        }

        public Builder districtCode(String districtCode) {
            this.districtCode = districtCode;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public Builder subprefectureCode(String subprefectureCode) {
            this.subprefectureCode = subprefectureCode;
            return this;
        }

        public Builder subprefecture(String subprefecture) {
            this.subprefecture = subprefecture;
            return this;
        }

        public Builder region5(String region5) {
            this.region5 = region5;
            return this;
        }

        public Builder region8(String region8) {
            this.region8 = region8;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder neighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public Builder landmark(String landmark) {
            this.landmark = landmark;
            return this;
        }

        ;

        public Fair build() throws Exception {
            if (null == registry || registry.trim().isEmpty()) {
                throw new Exception("The fair entity cannot be built because the registry is required");
            }

            var fair = new Fair();

            fair.id = id;
            fair.lat = lat;
            fair.lon = lon;
            fair.setcems = setcems;
            fair.weightingArea = weightingArea;
            fair.districtCode = districtCode;
            fair.district = district;
            fair.subprefectureCode = subprefectureCode;
            fair.subprefecture = subprefecture;
            fair.region5 = region5;
            fair.region8 = region8;
            fair.name = name;
            fair.registry = registry;
            fair.address = address;
            fair.number = number;
            fair.neighborhood = neighborhood;
            fair.landmark = landmark;

            return fair;
        }

    }

    private Fair() {
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
