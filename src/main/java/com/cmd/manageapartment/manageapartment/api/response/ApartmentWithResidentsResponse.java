package com.cmd.manageapartment.manageapartment.api.response;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Residents;

import java.util.List;

public class ApartmentWithResidentsResponse {
    private Apartment apartment;
    private List<Residents> residents;

    public ApartmentWithResidentsResponse(Apartment apartment, List<Residents> residents) {
        this.apartment = apartment;
        this.residents = residents;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<Residents> getResidents() {
        return residents;
    }

    public void setResidents(List<Residents> residents) {
        this.residents = residents;
    }
}
