package aemconnector.request;

public class AEMConnectorSearchRequestPage
{
    private int size;
    private String cursor;

    public AEMConnectorSearchRequestPage() {
    }

    public AEMConnectorSearchRequestPage(final int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public String getCursor() {
        return this.cursor;
    }

    public void setCursor(final String cursor) {
        this.cursor = cursor;
    }
}
