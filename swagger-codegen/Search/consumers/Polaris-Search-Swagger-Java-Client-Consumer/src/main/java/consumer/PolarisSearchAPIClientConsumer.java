package consumer;

import java.util.ArrayList;
import java.util.List;
import com.adobe.polarissearch.ApiClient;
import com.adobe.polarissearch.models.CompositeQuery;
import com.adobe.polarissearch.models.MatchQuery;
import com.adobe.polarissearch.models.MatchQueryMatch;
import com.adobe.polarissearch.models.SearchRequest;
import com.adobe.polarissearch.models.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.client.api.ActivatedAssetsSearchApi;

public class PolarisSearchAPIClientConsumer {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String POLARIS_DELIVERY_TIER = "https://delivery-p47604-e144858-cmstg.adobeaemcloud.com";
    private final static String POLARIS_SEARCH_SERVICE_API_KEY = "polaris-asset-search-api-key";
    private final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private final static String MATCH_TEXT = "sprintdemo";

    public void consume(final String token) {
        System.out.println("Consumer Main Swagger Polaris Search");
        System.out.println(System.getProperty("java.class.path"));

        final ActivatedAssetsSearchApi apiInstance = new ActivatedAssetsSearchApi();

        final ApiClient apiClient = apiInstance.getApiClient();
        apiClient.setBasePath(POLARIS_DELIVERY_TIER);
        apiClient.setApiKey(POLARIS_SEARCH_SERVICE_API_KEY);
        apiClient.setAccessToken(token);
        apiClient.selectHeaderContentType(new String[] { CONTENT_TYPE_APPLICATION_JSON });


        final MatchQueryMatch matchQuerymatch = new MatchQueryMatch();
        matchQuerymatch.text(MATCH_TEXT);

        final MatchQuery matchQuery = new MatchQuery();
        matchQuery.match(matchQuerymatch);

        final List<CompositeQuery> queryItems = new ArrayList<>();
        queryItems.add(matchQuery);

        SearchRequest request = new SearchRequest();
        request.query(queryItems);

        request.limit(50);
        request.offset(Integer.valueOf(0));


        try {
            SearchResponse result = apiInstance.search(request);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (Exception e) {
            System.err.println("Exception when calling ActivatedAssetsSearchApi#search");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PolarisSearchAPIClientConsumer obj = new PolarisSearchAPIClientConsumer();
        String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEtc3RnMS1rZXktYXQtMS5jZXIiLCJraWQiOiJpbXNfbmExLXN0ZzEta2V5LWF0LTEiLCJpdHQiOiJhdCJ9.eyJpZCI6IjE2ODA0OTc4Mzk1MTRfYWQ4MzI3MzAtOWVjZi00ZGI3LTg5OWItNmJlZDkwMjY5ZjA2X3VlMSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjbGllbnRfaWQiOiJ0ZXN0X3BvbGFyaXNfYXMiLCJ1c2VyX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsInN0YXRlIjoie1wianNsaWJ2ZXJcIjpcInYyLXYwLjMxLjAtMi1nMWU4YThhOFwiLFwibm9uY2VcIjpcIjQ0MzY4MTk0NjY4NjYyOTRcIixcImltc2xpYm1vZGFsXCI6dHJ1ZX0iLCJhcyI6Imltcy1uYTEtc3RnMSIsImFhX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsImN0cCI6MCwiZmciOiJYS1BFVDZSNDZSN1hDNERKN0daTUEyWUFJUT09PT09PSIsInNpZCI6IjE2ODA0OTc4Mzg3NjNfZWZjMzJlOTktYTU1ZS00OGNmLTllODEtNGU4NjliYWI3NjQ2X3VlMSIsIm1vaSI6IjkxNjBkNWE5IiwicGJhIjoiT1JHLE1lZFNlY05vRVYsTG93U2VjIiwiZXhwaXJlc19pbiI6IjI4Nzk0NDg2IiwiY3JlYXRlZF9hdCI6IjE2ODA0OTc4Mzk1MTQiLCJzY29wZSI6ImFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxvcGVuaWQscmVhZF9vcmdhbml6YXRpb25zLEFkb2JlSUQifQ.MAE9LohzdXeIrFID8_bYBKsPFOCUF4u-guXuscwNRJePLIQcKVyyakQW4-1yWbnY1iTDcJu4Dh2VSoZ0d6WnrHCJEJOf5SRQRQdOP7xDdfYLsNHFiTyQJ96dXV5mmFWeWLb3b4TFxjBPADw_ZMVmELEviqdP2KB0kkFAIdZ6dKQEG0OriAn_sN6886Rz_1xAwXqhK8X-StEaW9Mns-qG1W9jRa4r-tP8HdV84GaaGzhgXim4SolIcFnjVTxdJKXKFsY_iYBfMdaUv8UVqj7cM1SSIZMlu4ftIj6UPdVFR-woLSDAtcwqp3DySmJ91IdDNZPnS4f_66pZVoo8fLvCZQ";
        obj.consume(token);
        System.out.println("done");
    }
}
