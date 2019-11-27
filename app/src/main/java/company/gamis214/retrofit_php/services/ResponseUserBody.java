package company.gamis214.retrofit_php.services;

import java.util.List;

public class ResponseUserBody {

    private List<UsersResponse> usersResponse;

    public List<UsersResponse> getUsers() {
        return usersResponse;
    }

    public void setUsers(List<UsersResponse> usersResponses) {
        this.usersResponse = usersResponse;
    }
}
