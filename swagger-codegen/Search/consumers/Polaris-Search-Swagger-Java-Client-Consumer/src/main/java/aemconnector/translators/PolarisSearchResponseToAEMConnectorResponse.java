package aemconnector.translators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import aemconnector.response.AEMConnectorSearchResponse;
import io.swagger.client.model.Asset;
import io.swagger.client.model.SearchResponse;

public class PolarisSearchResponseToAEMConnectorResponse {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    /*
     * the searchCountLimit is used to detect "has more pages".
     */
    public AEMConnectorSearchResponse translateFrom(final SearchResponse polarisSearchResponse, final int searchCountLimit) throws IOException {

        String aemConnectorResponseJson = this.getString(PolarisSearchResponseToAEMConnectorResponse.class.getClassLoader()
            .getResourceAsStream("jsons/aem-connector-response.json"));
        final AEMConnectorSearchResponse aemConnectorSearchResponseFile
            = new ObjectMapper().readValue(aemConnectorResponseJson, AEMConnectorSearchResponse.class);



        final AEMConnectorSearchResponse aemConnectorSearchResponse = new AEMConnectorSearchResponse();

        // count and hasMore
        setCountAndHasMore(polarisSearchResponse, searchCountLimit, aemConnectorSearchResponse);

        // assets (map<string, object>)
        int searchHitCount = polarisSearchResponse.getSearchMetadata().getCount();
        if(searchHitCount > searchCountLimit) {
            searchHitCount--;
        }
        List<Map<String, Object>> assets = new ArrayList<>();
        for(int i = 0; i < searchHitCount; i++) {
            final Asset asset = polarisSearchResponse.getHits().getResults().get(i);

            final Map<String, Object> assetMetadata = (Map<String, Object>) asset.getMetadata();
            final Map<String, Object> embeddedMetadata = (Map<String, Object>) assetMetadata.get("embedded");
            final Map<String, Object> repositoryMetadata = (Map<String, Object>) assetMetadata.get("repository");
            final Map<String, Object> applicationMetadata = (Map<String, Object>) assetMetadata.get("application");

            // aem connector response
            final Map<String, Object> assetProperties = new HashMap<>();

            // jcr:createDate
            addJcrCreated(repositoryMetadata, assetProperties);

            // jcr:content
            final String modifiedDateStr = repositoryMetadata.get("repo:modifyDate").toString();
            final LocalDateTime lastModified = LocalDateTime.parse(modifiedDateStr, dateTimeFormatter);
            assetProperties.put("jcr:content", new HashMap<String, Object>(){{
                put("jcr:lastModified", new HashMap<String, Integer>(){{
                    put("year", Integer.valueOf(lastModified.getYear()));
                    put("month", Integer.valueOf(lastModified.getMonthValue()));
                    put("dayOfMonth", Integer.valueOf(lastModified.getDayOfMonth()));
                    put("hourOfDay", Integer.valueOf(lastModified.getHour()));
                    put("minute", Integer.valueOf(lastModified.getMinute()));
                    put("second", Integer.valueOf(lastModified.getSecond()));
                }});
                // jcr:content/metadata

                // jcr:uuid

                // jcr:primaryType

                // renditions


            }});


            assets.add(assetProperties);
        }
        aemConnectorSearchResponse.setAssets(assets);


        // facets

        return aemConnectorSearchResponseFile;
//        return aemConnectorSearchResponse;

//        final AEMConnectorSearchResponse aemConnectorSearchResponse = new AEMConnectorSearchResponse();
//
//        // count and hasMore
//        int searchHitsCount = polarisSearchResponse.getSearchMetadata().getCount().intValue();
//        if(searchHitsCount > searchCountLimit) {
//            aemConnectorSearchResponse.setCount(Long.valueOf(searchHitsCount));
//            aemConnectorSearchResponse.setHasMore(true);
//        } else {
//            aemConnectorSearchResponse.setHasMore(false);
//        }
//
//
//        for(int i = 0; i < (searchHitsCount <= searchCountLimit ? searchHitsCount : searchCountLimit); i++) {
//            final Asset asset = polarisSearchResponse.getHits().getResults().get(i);
//        }
//
//        return null;
    }

    private void addJcrCreated(Map<String, Object> repositoryMetadata, Map<String, Object> assetProperties) {

        final String createdDateStr = repositoryMetadata.get("repo:createDate").toString();
        final LocalDateTime createdDateTime = LocalDateTime.parse(createdDateStr, dateTimeFormatter);
        assetProperties.put("jcr:created", new HashMap<String, Integer>(){{
            put("year", Integer.valueOf(createdDateTime.getYear()));
            put("month", Integer.valueOf(createdDateTime.getMonthValue()));
            put("dayOfMonth", Integer.valueOf(createdDateTime.getDayOfMonth()));
            put("hourOfDay", Integer.valueOf(createdDateTime.getHour()));
            put("minute", Integer.valueOf(createdDateTime.getMinute()));
            put("second", Integer.valueOf(createdDateTime.getSecond()));
        }});
    }

    private void setCountAndHasMore(SearchResponse polarisSearchResponse, int searchCountLimit, AEMConnectorSearchResponse aemConnectorSearchResponse) {
        long searchHitCount = polarisSearchResponse.getSearchMetadata().getCount().longValue();
        if(searchHitCount > searchCountLimit) {
            searchHitCount--;
            aemConnectorSearchResponse.setHasMore(true);
        } else {
            aemConnectorSearchResponse.setHasMore(false);
        }
        aemConnectorSearchResponse.setCount(searchHitCount);
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
