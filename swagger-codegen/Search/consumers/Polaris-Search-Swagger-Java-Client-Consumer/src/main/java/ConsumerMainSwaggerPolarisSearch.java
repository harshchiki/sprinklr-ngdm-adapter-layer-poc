import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.client.ApiClient;
import io.swagger.client.api.ActivatedAssetsSearchApi;
import io.swagger.client.model.CompositeQuery;
import io.swagger.client.model.MatchQuery;
import io.swagger.client.model.MatchQueryMatch;
import io.swagger.client.model.SearchRequest;
import io.swagger.client.model.SearchResponse;


public class ConsumerMainSwaggerPolarisSearch {
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

        // set query
//        QueryParams body = new QueryParams();
//        body.query(new ArrayList<CompositeQuery>());
//        final MatchQueryMatch matchQueryMatch = new MatchQueryMatch();
//        matchQueryMatch.text("crocs");
//        final MatchQuery matchQuery = new MatchQuery();
//        matchQuery.match(matchQueryMatch);
//        body.addQueryItem(matchQuery);
//
//        // hits per page
//        body.setHitsPerPage(Integer.valueOf(50));
//
//        // offset
//        body.setOffset(Integer.valueOf(1));
//
//        try {
//            SearchResults result = apiInstance.search(body);
//            ObjectMapper objectMapper = new ObjectMapper();
//            System.out.println(objectMapper.writeValueAsString(result));
//        } catch (Exception e) {
//            System.err.println("Exception when calling ActivatedAssetsSearchApi#search");
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {
//        if(args.length == 0) {
//            System.out.println("Missing access token!!");
//            return;
//        }

//        final String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEtc3RnMS1rZXktYXQtMS5jZXIiLCJraWQiOiJpbXNfbmExLXN0ZzEta2V5LWF0LTEiLCJpdHQiOiJhdCJ9.eyJpZCI6IjE2Nzg4NzMxOTMxMTBfYmEyZTlhYzQtZjlkYy00MjJkLTg0OTgtOGI3MjJjY2E5YmU1X3VlMSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjbGllbnRfaWQiOiJleGNfYXBwIiwidXNlcl9pZCI6IjhGOTQwQjk4NUNENDQ5REYwQTQ5NDIxQ0BjNjJmMjRjYzViNWI3ZTBlMGE0OTQwMDQiLCJzdGF0ZSI6IntcImpzbGlidmVyXCI6XCJ2Mi12MC4zMS4wLTItZzFlOGE4YThcIixcIm5vbmNlXCI6XCIxMDgwMTQzMjA4MTk2OTQ3XCIsXCJzZXNzaW9uXCI6XCJodHRwczovL2ltcy1uYTEtc3RnMS5hZG9iZWxvZ2luLmNvbS9pbXMvc2Vzc2lvbi92MS9ZbU0yTVdOa016QXRaREkxTUMwME1XVmhMV0ppWkdNdFpETXpPR1F5TkdRd1pXTTFMUzA0UmprME1FSTVPRFZEUkRRME9VUkdNRUUwT1RReU1VTkFZell5WmpJMFkyTTFZalZpTjJVd1pUQmhORGswTURBMFwifSIsImFzIjoiaW1zLW5hMS1zdGcxIiwiYWFfaWQiOiI4Rjk0MEI5ODVDRDQ0OURGMEE0OTQyMUNAYzYyZjI0Y2M1YjViN2UwZTBhNDk0MDA0IiwiY3RwIjowLCJmZyI6IlhJMklINlI0NlI3WEM0REozR1pNQTJZQUVBPT09PT09Iiwic2lkIjoiMTY3ODg3MzE5MjM0MV8xZjRjZTJlNy04OWU1LTRlMzgtYmM5YS0zZTU0NGJmYmFkZmRfdWUxIiwibW9pIjoiMjQ1OTUwZjYiLCJwYmEiOiJPUkcsTWVkU2VjTm9FVixMb3dTZWMiLCJleHBpcmVzX2luIjoiMjg3OTY4OTEiLCJzY29wZSI6ImFiLm1hbmFnZSxhZGRpdGlvbmFsX2luZm8sYWRkaXRpb25hbF9pbmZvLmpvYl9mdW5jdGlvbixhZGRpdGlvbmFsX2luZm8ucHJvamVjdGVkUHJvZHVjdENvbnRleHQsYWRkaXRpb25hbF9pbmZvLnJvbGVzLEFkb2JlSUQsYWRvYmVpby5hcHByZWdpc3RyeS5yZWFkLGFkb2JlaW9fYXBpLGF1ZGllbmNlbWFuYWdlcl9hcGksY3JlYXRpdmVfY2xvdWQsbXBzLG9wZW5pZCxvcmcucmVhZCxyZWFkX29yZ2FuaXphdGlvbnMscmVhZF9wYyxyZWFkX3BjLmFjcCxyZWFkX3BjLmRtYV90YXJ0YW4sc2Vzc2lvbiIsImNyZWF0ZWRfYXQiOiIxNjc4ODczMTkzMTEwIn0.ImWgYAmOHmDmOTVlKDKaCEAR4MfRJq8qtlUQvLOst8DirA_4YjGGCFsvZgaKy2dXGe65wQPOL99VyBCE3NiGgf9OHOWsx_HUAI7pLly0sNueT15jDJGxFlx0GhYDMwFKAegVm1GomP16UVMW824Pb9H1deTq7BqOevEXacwjEMOzqN68pq7-51CizzEOpegyUpWu3HC9e221H3tIfY-4vKfEM0fGadoMCSaY_rEAP5febLj-MhN2UTBQU2q_8mAhyPIH9XJNr41qjRRVHh9OkmMIDBDb0SY5rxDa_mX7wqMHwwFJfJqHp2a7JmEAl6R9HZuEnNWq12bzlW7GzXy_9Q";
        final String token = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEtc3RnMS1rZXktYXQtMS5jZXIiLCJraWQiOiJpbXNfbmExLXN0ZzEta2V5LWF0LTEiLCJpdHQiOiJhdCJ9.eyJpZCI6IjE2ODAxODI1MzA1MzNfZTBkM2FjNTEtZTExOS00ZTJlLTllMDAtZmZlOGJkMzgyOWRjX3VlMSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjbGllbnRfaWQiOiJ0ZXN0X3BvbGFyaXNfYXMiLCJ1c2VyX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsInN0YXRlIjoie1wianNsaWJ2ZXJcIjpcInYyLXYwLjMxLjAtMi1nMWU4YThhOFwiLFwibm9uY2VcIjpcIjI5NjAxOTg5NzgyNDQ1MTRcIixcImltc2xpYm1vZGFsXCI6dHJ1ZX0iLCJhcyI6Imltcy1uYTEtc3RnMSIsImFhX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsImN0cCI6MCwiZmciOiJYS0U0RjZSNDZSN1hDNERKN0daTUEyWUFJUT09PT09PSIsInNpZCI6IjE2ODAxODI1MjkxMTBfOGE5NDAxZDAtZTNjYy00OTkwLTg2OTItYWUyOWNmODgxZTI1X3VlMSIsIm1vaSI6ImM1ZTQyODk5IiwicGJhIjoiT1JHLE1lZFNlY05vRVYsTG93U2VjIiwiZXhwaXJlc19pbiI6IjI4Nzk0NDY3IiwiY3JlYXRlZF9hdCI6IjE2ODAxODI1MzA1MzMiLCJzY29wZSI6ImFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxvcGVuaWQscmVhZF9vcmdhbml6YXRpb25zLEFkb2JlSUQifQ.KCofIAVRpSR6qcJFPVtITQX0qKLmmkn8wcg7TjQrN8lA84eZoFYaEAatw_Vyb2xRBjyX-SDXF6BIYk0i5YUuBRSUCqASY_zfFmDVYwTy8kGOqrE9lUrP1hxyOe9YxaiCW6FAvk2cGe_5bqHDPNM5VSbfozO_CVdOCKCNA9BORxHMD-BWQoBX2UQyGpX9YGcAu6smw6qLCY3p_z-4lauwYTGmv4fcQSTnC5oJ2iQawJ9oykX4lBiJdAp9yK9H0fdinCo53Gr3QglUF_iQbu-KAdIMtTNJTIT7yVLjSqGcIIDSHDv6Ce8KFfrs-x1jl_wtTAKqimgGIyo-Icf7Q26iUA";
        ConsumerMainSwaggerPolarisSearch obj = new ConsumerMainSwaggerPolarisSearch();
        obj.consume(token);
        System.out.println("done");
    }
}
