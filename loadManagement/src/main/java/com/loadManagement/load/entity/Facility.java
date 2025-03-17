package com.loadManagement.load.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Facility {
    private String loadingPoint;
    private String unloadingPoint;
    private String loadingDate;
    private String unloadingDate;
}
