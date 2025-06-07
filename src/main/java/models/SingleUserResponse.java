package models;

public class SingleUserResponse {
    private UserDataResponse data;
    private Support support;

    public UserDataResponse getData() {
        return data;
    }
    public void setData(UserDataResponse data) {
        this.data = data;
    }
    public Support getSupport() {
        return support;
    }
    public void setSupport(Support support) {
        this.support = support;
    }
}
