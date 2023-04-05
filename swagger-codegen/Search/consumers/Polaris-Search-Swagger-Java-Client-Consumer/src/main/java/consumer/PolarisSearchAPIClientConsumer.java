package consumer;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ActivatedAssetsSearchApi;
import io.swagger.client.model.CompositeQuery;
import io.swagger.client.model.MatchQuery;
import io.swagger.client.model.MatchQueryMatch;
import io.swagger.client.model.SearchRequest;
import io.swagger.client.model.SearchResponse;

public class PolarisSearchAPIClientConsumer {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String POLARIS_DELIVERY_TIER = "https://delivery-p47604-e144858-cmstg.adobeaemcloud.com";
    private final static String POLARIS_SEARCH_SERVICE_API_KEY = "polaris-asset-search-api-key";
    private final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private final static String MATCH_TEXT = "sprintdemo";

    public SearchResponse call(final SearchRequest polarisSearchRequest, final String token) throws JsonProcessingException, ApiException {
        final ActivatedAssetsSearchApi apiInstance = new ActivatedAssetsSearchApi();

        final ApiClient apiClient = apiInstance.getApiClient();
        apiClient.setBasePath(POLARIS_DELIVERY_TIER);
        apiClient.setApiKey(POLARIS_SEARCH_SERVICE_API_KEY);
        apiClient.setAccessToken(token);
        apiClient.selectHeaderContentType(new String[] { CONTENT_TYPE_APPLICATION_JSON });

        try {
            SearchResponse result = apiInstance.search(polarisSearchRequest);
            System.out.println("Printing the Polaris Search Response");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
            return result;
        } catch (Exception e) {
            System.err.println("Exception when calling ActivatedAssetsSearchApi#search");
            e.printStackTrace();
            throw e;
        }
    }
}
