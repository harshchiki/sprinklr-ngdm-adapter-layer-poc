package aemconnector.request;

import java.util.ArrayList;
import java.util.List;

public class AEMConnectorSearchRequestFilter
{
    private FilterType type;
    private List<AEMConnectorSearchRequestFilter> AEMConnectorSearchRequestFilters;
    private String key;
    private List<String> values;

    public FilterType getType() {
        return this.type;
    }

    public void setType(final FilterType type) {
        this.type = type;
    }

    public List<AEMConnectorSearchRequestFilter> getFilters() {
        return this.AEMConnectorSearchRequestFilters;
    }

    public void setFilters(final List<AEMConnectorSearchRequestFilter> AEMConnectorSearchRequestFilters) {
        this.AEMConnectorSearchRequestFilters = AEMConnectorSearchRequestFilters;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public List<String> getValues() {
        return this.values;
    }

    public void setValues(final List<String> values) {
        this.values = values;
    }

    public AEMConnectorSearchRequestFilter type(final FilterType type) {
        this.type = type;
        return this;
    }

    public AEMConnectorSearchRequestFilter key(final String key) {
        this.key = key;
        return this;
    }

    public AEMConnectorSearchRequestFilter values(final List<String> values) {
        this.values = values;
        return this;
    }

    public AEMConnectorSearchRequestFilter filters(final List<AEMConnectorSearchRequestFilter> AEMConnectorSearchRequestFilters) {
        this.AEMConnectorSearchRequestFilters = AEMConnectorSearchRequestFilters;
        return this;
    }

    public AEMConnectorSearchRequestFilter addFilter(final AEMConnectorSearchRequestFilter AEMConnectorSearchRequestFilter) {
        if (this.AEMConnectorSearchRequestFilters == null || this.AEMConnectorSearchRequestFilters.isEmpty()) {
            this.AEMConnectorSearchRequestFilters = new ArrayList<AEMConnectorSearchRequestFilter>();
        }
        this.AEMConnectorSearchRequestFilters.add(AEMConnectorSearchRequestFilter);
        return this;
    }

    public enum FilterType
    {
        AND,
        OR,
        IN,
        GT,
        GTE,
        LT,
        LTE,
        EQUALS;
    }
}