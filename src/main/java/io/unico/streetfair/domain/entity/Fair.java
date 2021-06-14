package io.unico.streetfair.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fair {

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

    private String fair_name;

    @Id
    private String registry;

    private String address;

    private String number;

    private String neighborhoods;

    private String landmark;

    public String getRegistry() {
        return registry;
    }

}
