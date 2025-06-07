package models;

public class SingleResourceResponse {
    private ResourceDataResponse data;
    private Support support;

    public ResourceDataResponse getData() {
        return data;
    }
    public void setData(ResourceDataResponse data) {
        this.data = data;
    }
    public Support getSupport() {
        return support;
    }
    public void setSupport(Support support) {
        this.support = support;
    }
}
