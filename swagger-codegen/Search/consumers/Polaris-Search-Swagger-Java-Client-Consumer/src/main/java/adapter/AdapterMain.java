package adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import aemconnector.request.AEMConnectorSearchRequest;
import aemconnector.response.AEMConnectorSearchResponse;
import aemconnector.translators.AEMConnectorRequestToPolarisSearchRequest;
import aemconnector.translators.PolarisSearchResponseToAEMConnectorResponse;
import consumer.PolarisSearchAPIClientConsumer;
import io.swagger.client.ApiException;
import io.swagger.client.model.SearchRequest;
import io.swagger.client.model.SearchResponse;

public class AdapterMain {
    private final static String IMS_TOKEN = "eyJhbGciOiJSUzI1NiIsIng1dSI6Imltc19uYTEtc3RnMS1rZXktYXQtMS5jZXIiLCJraWQiOiJpbXNfbmExLXN0ZzEta2V5LWF0LTEiLCJpdHQiOiJhdCJ9.eyJpZCI6IjE2ODA2NjgyNDQ4NDZfYjU1NmM2ZWItZWYyYi00YzYzLWI3YzctMzJjOTIxOTllODkwX3VlMSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJjbGllbnRfaWQiOiJ0ZXN0X3BvbGFyaXNfYXMiLCJ1c2VyX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsInN0YXRlIjoie1wianNsaWJ2ZXJcIjpcInYyLXYwLjMxLjAtMi1nMWU4YThhOFwiLFwibm9uY2VcIjpcIjM4NzAxNTk5NjYxNzk5MzhcIixcImltc2xpYm1vZGFsXCI6dHJ1ZX0iLCJhcyI6Imltcy1uYTEtc3RnMSIsImFhX2lkIjoiOEY5NDBCOTg1Q0Q0NDlERjBBNDk0MjFDQGM2MmYyNGNjNWI1YjdlMGUwYTQ5NDAwNCIsImN0cCI6MCwiZmciOiJYS1VXRDZSNDZSN1hDNERKN0daTUEyWUFJUT09PT09PSIsInNpZCI6IjE2ODA2Mjg5Mjg1MTZfYmNhZjIyOGUtZTc4YS00MmRjLWIxYjItOTMyODU4ZDU0OTFhX3VlMSIsIm1vaSI6IjU3ODZjMWY4IiwicGJhIjoiT1JHLE1lZFNlY05vRVYsTG93U2VjIiwiZXhwaXJlc19pbiI6IjI4Nzk3MTU0IiwiY3JlYXRlZF9hdCI6IjE2ODA2NjgyNDQ4NDYiLCJzY29wZSI6ImFkZGl0aW9uYWxfaW5mby5wcm9qZWN0ZWRQcm9kdWN0Q29udGV4dCxvcGVuaWQscmVhZF9vcmdhbml6YXRpb25zLEFkb2JlSUQifQ.JGE6TjDC_SS87KViFEqiII1840TY8r1DyFqGXB0DNqq9KR2FfG3TRhZvx4uVtRoM0e98RQ4sVE1qiFopbXoDTx9Mh0FftiQunsqBrV9MfTIJ3dzha6E2Vi0ot1pkDKh12IQVgQ6OCoZ13UXz3jJn65eV97LnjtGg-PJLDuaOVe2z_GmhUuqBLGF2IAbClHfJaTXmkDCV74BWY-rYNybfMHnuRHoJlZOb3dVHiWnswCoh7ifPT-xDQBGUV7theY3VwA5NkMR67NzpRcct8644yv3yxgkoG0ynQmPJXe97jd-f_pRHN3NlR8jY7PGAU2uXsqViqd9c-6iniWo8p6tXAQ";
    private final static int LIMIT = 40;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final PolarisSearchResponseToAEMConnectorResponse polarisSearchResponseToAEMConnectorResponse
        = new PolarisSearchResponseToAEMConnectorResponse();
    private static final PolarisSearchAPIClientConsumer polarisSearchAPIClientConsumer
        = new PolarisSearchAPIClientConsumer();

    public static void main(String[] args) throws IOException, ApiException {
        AdapterMain adapterMain = new AdapterMain();

        // construct AEMConnectorSearchRequest from aemConnReqJsonStr
        String aemConnectedRequestJson = adapterMain.getString(AdapterMain.class.getClassLoader()
            .getResourceAsStream("jsons/aem-connector-request.json"));
        AEMConnectorSearchRequest aemConnectorSearchRequest
            = mapper.readValue(aemConnectedRequestJson, AEMConnectorSearchRequest.class);

        // construct Polaris Search Request
        SearchRequest polarisSearchRequest
            = new AEMConnectorRequestToPolarisSearchRequest().translateFrom(aemConnectorSearchRequest);


        // call PolarisSearchAPIClientConsumer to perform Polaris Search
        SearchResponse polarisSearchResponse = polarisSearchAPIClientConsumer.call(polarisSearchRequest, IMS_TOKEN);


        // construct AEMConnectorSearchResponse from received SearchResponse
        final AEMConnectorSearchResponse aemConnectorSearchResponse =
            polarisSearchResponseToAEMConnectorResponse.translateFrom(polarisSearchResponse, LIMIT);

        System.out.println("Printing translated AEM Connector Response");
        System.out.println(mapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(aemConnectorSearchResponse));
        System.out.println("END: Printed AEM Connector Response JSON");
    }

    private String getString(final InputStream is) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
            (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
