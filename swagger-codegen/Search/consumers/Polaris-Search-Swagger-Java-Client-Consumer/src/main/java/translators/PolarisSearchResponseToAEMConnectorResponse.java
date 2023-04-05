package translators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import aemconnector.response.AEMConnectorSearchResponse;
import consumer.PolarisSearchAPIClientConsumer;
import io.swagger.client.model.Asset;
import io.swagger.client.model.SearchResponse;

public class PolarisSearchResponseToAEMConnectorResponse {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    /*
     * the searchCountLimit is used to detect "has more pages".
     */
    public AEMConnectorSearchResponse translateFrom(final SearchResponse polarisSearchResponse,
                                                    final int searchCountLimit) throws IOException {

        final AEMConnectorSearchResponse aemConnectorSearchResponse = new AEMConnectorSearchResponse();

        // count and hasMore
        setCountAndHasMore(polarisSearchResponse, searchCountLimit, aemConnectorSearchResponse);

        // aemConnectorSearchResponseAssets (map<string, object>)
        int searchHitCount = polarisSearchResponse.getSearchMetadata().getCount();
        if(searchHitCount > searchCountLimit) {
            searchHitCount--;
        }

        final List<Map<String, Object>> aemConnectorSearchResponseAssets = new ArrayList<>();
        for(int i = 0; i < searchHitCount; i++) {
            final Asset asset = polarisSearchResponse.getHits().getResults().get(i);

            final Map<String, Object> assetMetadata = (Map<String, Object>) asset.getMetadata();
            final Map<String, Object> embeddedMetadata = (Map<String, Object>) assetMetadata.get("embedded");
            final Map<String, Object> repositoryMetadata = (Map<String, Object>) assetMetadata.get("repository");
            final Map<String, Object> applicationMetadata = (Map<String, Object>) assetMetadata.get("application");

            // aem connector response
            final Map<String, Object> aemConnectorResponseAssetProperties = new HashMap<>();

            // jcr:createDate
            final String createdDateStr = repositoryMetadata.get("repo:createDate").toString();
            final LocalDateTime createdDateTime = LocalDateTime.parse(createdDateStr, dateTimeFormatter);
            aemConnectorResponseAssetProperties.put("jcr:created", new HashMap<String, Integer>(){{
                put("year", Integer.valueOf(createdDateTime.getYear()));
                put("month", Integer.valueOf(createdDateTime.getMonthValue()));
                put("dayOfMonth", Integer.valueOf(createdDateTime.getDayOfMonth()));
                put("hourOfDay", Integer.valueOf(createdDateTime.getHour()));
                put("minute", Integer.valueOf(createdDateTime.getMinute()));
                put("second", Integer.valueOf(createdDateTime.getSecond()));
            }});

            // jcr:createdBy and jcr:modifiedBy - TODO

            // jcr:content
            final String modifiedDateStr = repositoryMetadata.get("repo:modifyDate").toString();
            final LocalDateTime lastModified = LocalDateTime.parse(modifiedDateStr, dateTimeFormatter);
            aemConnectorResponseAssetProperties.put("jcr:content", new HashMap<String, Object>(){{
                put("jcr:lastModified", new HashMap<String, Integer>(){{
                    put("year", Integer.valueOf(lastModified.getYear()));
                    put("month", Integer.valueOf(lastModified.getMonthValue()));
                    put("dayOfMonth", Integer.valueOf(lastModified.getDayOfMonth()));
                    put("hourOfDay", Integer.valueOf(lastModified.getHour()));
                    put("minute", Integer.valueOf(lastModified.getMinute()));
                    put("second", Integer.valueOf(lastModified.getSecond()));
                }});
                // jcr:content/metadata
                put("jcr:content", new HashMap<String, Object>(){{
                    put("metadata", new HashMap<String, Object>(){{
                        if(applicationMetadata.containsKey("dc:description")) {
                            put("dc:description", applicationMetadata.get("dc:description").toString());
                        }
                        put("dc:Fileformat", FilenameUtils.getExtension(repositoryMetadata.get("repo:name").toString()));
                        put("dc:format", repositoryMetadata.get("dc:format").toString());
                        put("dc:MIMEtype", repositoryMetadata.get("dc:format").toString());
                        put("tiff:ImageLength", embeddedMetadata.get("tiff:ImageLength"));
                        put("tiff:ImageWidth", embeddedMetadata.get("tiff:ImageWidth"));
                        if(applicationMetadata.containsKey("dc:title")) {
                            put("dc:title", applicationMetadata.get("dc:title").toString());
                        }
                        put("dam:status", applicationMetadata.get("dam:assetStatus"));
                    }});
                }});

                // jcr:uuid
                put("jcr:uuid", asset.getId());

                // jcr:primaryType
                put("jcr:primaryType", "dam:Asset");

                // renditions
                final String assetDeliveryURL = PolarisSearchAPIClientConsumer.POLARIS_DELIVERY_TIER
                    + "/adobe/dynamicmedia/deliver/" + asset.getId() + "/"
                    + URLEncoder.encode(repositoryMetadata.get("repo:name").toString(), "UTF-8");

                put("renditions", Arrays.asList(
                    new HashMap<String, String>() {{
                        put("path", assetDeliveryURL + "?width=140&height=100&preferwebp=true");
                        put("name", "cq5dam.thumbnail.140.100.png");
                    }},
                    new HashMap<String, String>() {{
                        put("path", assetDeliveryURL+ "?width=319&height=319&preferwebp=true");
                        put("name", "cq5dam.thumbnail.319.319.png");
                    }},
                    new HashMap<String, String>() {{
                        put("path", assetDeliveryURL+ "?width=48&height=48&preferwebp=true");
                        put("name", "cq5dam.thumbnail.48.48.png");
                    }},
                    new HashMap<String, String>() {{
                        put("path", assetDeliveryURL+ "?width=1280&height=1280&preferwebp=true");
                        put("name", "cq5dam.web.1280.1280.jpeg");
                    }},
                    new HashMap<String, String>() {{
                        put("path", assetDeliveryURL+ "?width=2048&height=2048&preferwebp=true");
                        put("name", "cq5dam.zoom.2048.2048.jpeg");
                    }}
                ));
            }});

            aemConnectorSearchResponseAssets.add(aemConnectorResponseAssetProperties);
        }
        aemConnectorSearchResponse.setAssets(aemConnectorSearchResponseAssets);

        // facets - TODO
        return aemConnectorSearchResponse;
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
