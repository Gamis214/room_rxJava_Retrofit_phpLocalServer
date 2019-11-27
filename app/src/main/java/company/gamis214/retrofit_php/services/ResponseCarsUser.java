package company.gamis214.retrofit_php.services;

import java.util.List;

public class ResponseCarsUser {

    private List<CarsUserList> carsUserList;

    public List<CarsUserList> getCarsUserList() {
        return carsUserList;
    }

    public void setCarsUserList(List<CarsUserList> carsUserList) {
        this.carsUserList = carsUserList;
    }
}
