package aemconnector.response;

import java.util.Map;
import java.util.List;

public class AEMConnectorSearchResponse
{
    private List<Map<String, Object>> assets;
    private Map<String, Object> facets;
    private long count;
    private boolean hasMore;

    public List<Map<String, Object>> getAssets() {
        return this.assets;
    }

    public void setAssets(final List<Map<String, Object>> assets) {
        this.assets = assets;
    }

    public Map<String, Object> getFacets() {
        return this.facets;
    }

    public void setFacets(final Map<String, Object> facets) {
        this.facets = facets;
    }

    public boolean hasMore() {
        return this.hasMore;
    }

    public void setHasMore(final boolean hasMore) {
        this.hasMore = hasMore;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(final long count) {
        this.count = count;
    }
}
