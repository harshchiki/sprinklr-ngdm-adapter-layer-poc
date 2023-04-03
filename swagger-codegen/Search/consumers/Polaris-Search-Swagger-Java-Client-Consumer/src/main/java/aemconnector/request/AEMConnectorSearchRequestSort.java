package aemconnector.request;

public class AEMConnectorSearchRequestSort
{
    private String key;
    private AEMConnectorSearchRequestOrder AEMConnectorSearchRequestOrder;

    public AEMConnectorSearchRequestSort() {
        this.AEMConnectorSearchRequestOrder = AEMConnectorSearchRequestOrder.DESC;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public AEMConnectorSearchRequestOrder getOrder() {
        return this.AEMConnectorSearchRequestOrder;
    }

    public void setOrder(final AEMConnectorSearchRequestOrder AEMConnectorSearchRequestOrder) {
        this.AEMConnectorSearchRequestOrder = AEMConnectorSearchRequestOrder;
    }
}