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

public class AdapterMain {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        AdapterMain adapterMain = new AdapterMain();

        // construct AEMConnectorSearchRequest from aemConnReqJsonStr
        String aemConnectedRequestJson = adapterMain.getString(AdapterMain.class.getClassLoader()
            .getResourceAsStream("jsons/aem-connector-request.json"));
        AEMConnectorSearchRequest aemConnectorSearchRequest
            = mapper.readValue(aemConnectedRequestJson, AEMConnectorSearchRequest.class);

        // call PolarisAdapter to adapt

        // construct AEMConnectorSearchResponse
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
