

//import com.adobe.polaris.search.api.ActivatedAssetsSearchApi;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SprinklrPolarisConnectorMain {
    private final static ObjectMapper mapper = new ObjectMapper();

    private final static String POLARIS_DELIVERY_TIER = "https://delivery-p47604-e144858-cmstg.adobeaemcloud.com";
    private final static String POLARIS_SEARCH_SERVICE_API_KEY = "polaris-asset-search-api-key";
    private final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    private final static String IMS_TOKEN = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEtc3RnMS1rZXktYXQtMS5jZXIiLCJraWQiOiJpbXNfbmExLXN0ZzEta2V5LWF0LTEiLCJpdHQiOiJhdCJ9.eyJpZCI6IjE2Nzk5ODExNTc3NDZfNmI0MTAyY2MtOTRlOC00YzNiLTllYzMtMzg2YjRlM2M2ZTI0X3VlMSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjbGllbnRfaWQiOiJ0ZXN0X3BvbGFyaXNfYXMiLCJ1c2VyX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsInN0YXRlIjoie1wic2Vzc2lvblwiOlwiaHR0cHM6Ly9pbXMtbmExLXN0ZzEuYWRvYmVsb2dpbi5jb20vaW1zL3Nlc3Npb24vdjEvTXprM05UVTNaRGN0TXpkak1pMDBNVEl4TFdJME56WXROak0wT1dFellqYzRPVGMzTFMwNFJqazBNRUk1T0RWRFJEUTBPVVJHTUVFME9UUXlNVU5BWXpZeVpqSTBZMk0xWWpWaU4yVXdaVEJoTkRrME1EQTBcIn0iLCJhcyI6Imltcy1uYTEtc3RnMSIsImFhX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsImN0cCI6MCwiZmciOiJYSjZLTDZSNDZSNzdDNERKN0daTUEyWUE3TT09PT09PSIsInNpZCI6IjE2Nzk5Nzk1NzM2NTRfODYwNWI2ZjQtMTNiMS00MjYyLTg2ZjktNzBkYzRmMGY4YWI1X3VlMSIsIm1vaSI6ImUzNjQ5MjliIiwicGJhIjoiT1JHLE1lZFNlY05vRVYsTG93U2VjIiwiZXhwaXJlc19pbiI6IjI3MjEzMjU0IiwiY3JlYXRlZF9hdCI6IjE2Nzk5ODExNTc3NDYiLCJzY29wZSI6ImFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxvcGVuaWQscmVhZF9vcmdhbml6YXRpb25zIn0.YuQ1VhGqpirdcEgZzi5c94pP8r26iotjiiqjs5fS-SqW0DZGEFmUQHDhe_uhDeBCVNk8t6oRkMYuoyT_C92PaJh2vzEHebR81goC8ksQy9KlocEoqMVkUfBbF5i7c1l8GKfqDrZTK9K-DCbGULWaAJhTh-rYA0X3tpyJujj_RU9e7bbGoG53pvAJTYbbC2MBX965iomwKzjcwWCvnjWDb7ZJ5R8-NW8omX8xBS0TuwTbPEvgWJjqFpiwkNSr8-Kp59hSjaqGPM6zuQ0lEXg1pKp-EGJZ6CPqV61Qtlw5PAh0JbK578dl_2X44dMb985X3zfk_pJpoKEw4lXA7oRSZw";

    private final static String MATCH_TEXT = "crocs";

    public static void main(String[] args) {
//        final ActivatedAssetsSearchApi api = new ActivatedAssetsSearchApi();

//        final ApiClient apiClient = api.getApiClient();
//        apiClient.setBasePath(POLARIS_DELIVERY_TIER);
//        apiClient.setApiKey(POLARIS_SEARCH_SERVICE_API_KEY);
//        apiClient.setAccessToken(IMS_TOKEN);
//        apiClient.selectHeaderContentType(new String[] { CONTENT_TYPE_APPLICATION_JSON });
//
//        // query
//        final List<CompositeQuery> queryItems = new ArrayList<>();
//        queryItems.add(new MatchQuery().match(new MatchQueryMatch().addFieldsItem(MATCH_TEXT)));
//
//        SearchRequest request = new SearchRequest();
//        request.query(queryItems);
//
//        request.limit(50);
//        request.offset(Integer.valueOf(0));
//
//
//        try {
//            SearchResponse result = api.search(request);
//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
//        } catch (Exception e) {
//            System.err.println("Exception when calling ActivatedAssetsSearchApi#search");
//            e.printStackTrace();
//        }
    }
}
