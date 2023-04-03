package aemconnector.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AEMConnectorSearchRequest
{
    @Getter
    @Setter
    @JsonProperty("q")
    String q;

    @Getter
    @Setter
    @JsonProperty("filter")
    AEMConnectorSearchRequestFilter AEMConnectorSearchRequestFilter;

    @Getter
    @Setter
    @JsonProperty("sorts")
    List<AEMConnectorSearchRequestSort> AEMConnectorSearchRequestSorts;

    @Getter
    @Setter
    @JsonProperty("page")
    AEMConnectorSearchRequestPage page;

    @Getter
    @Setter
    @JsonProperty("facets")
    List<String> facets;
}