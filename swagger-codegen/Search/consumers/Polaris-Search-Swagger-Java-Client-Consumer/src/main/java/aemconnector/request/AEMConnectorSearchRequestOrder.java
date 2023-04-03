package aemconnector.request;

public enum AEMConnectorSearchRequestOrder
{
    ASC("asc"),
    DESC("desc");

    private String order;

    private AEMConnectorSearchRequestOrder(final String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }
}
