package translators;

import aemconnector.request.AEMConnectorSearchRequest;
import aemconnector.request.AEMConnectorSearchRequestPage;
import io.swagger.client.model.MatchQuery;
import io.swagger.client.model.MatchQueryMatch;
import io.swagger.client.model.SearchRequest;
import io.swagger.client.model.Sortable;

public class AEMConnectorRequestToPolarisSearchRequest {
    public SearchRequest translateFrom(final AEMConnectorSearchRequest aemConnectorSearchRequest) {
        final SearchRequest polarisSearchRequest = new SearchRequest();
        // search text
        buildSearchText(aemConnectorSearchRequest, polarisSearchRequest);

        // filters

        // sort - needs mapping of properties
//        buildSorts(aemConnectorSearchRequest, polarisSearchRequest);


        // page params
        buildPageParams(aemConnectorSearchRequest);
        return polarisSearchRequest;
    }

    private void buildSorts(AEMConnectorSearchRequest aemConnectorSearchRequest, SearchRequest polarisSearchRequest) {
        aemConnectorSearchRequest.getAemConnectorSearchRequestSorts().forEach(sortCriteria -> {
            final Sortable sortable = new Sortable();
            sortable.by(sortCriteria.getKey());
            switch(sortCriteria.getOrder()) {
                case ASC:
                    sortable.by(Sortable.OrderEnum.ASC.getValue());
                    break;
                case DESC:
                    sortable.by(Sortable.OrderEnum.DESC.getValue());
                    break;
            }
            polarisSearchRequest.addSortItem(sortable);
        });
    }

    private void buildPageParams(final AEMConnectorSearchRequest aemConnectorSearchRequest) {
        final AEMConnectorSearchRequestPage pageParams = aemConnectorSearchRequest.getPage();
        final int limit = pageParams != null ? (pageParams.getSize() > 0 ? pageParams.getSize() : 50) : 50;
        final int offset = pageParams != null ?
            ((pageParams.getCursor() != null && pageParams.getCursor().length() > 0) ? Integer.parseInt(pageParams.getCursor()) : 0) : 0;
    }

    private void buildSearchText(final AEMConnectorSearchRequest aemConnectorSearchRequest, final SearchRequest polarisSearchRequest) {
        final String fullTextSearchStr = aemConnectorSearchRequest.getQ() != null ? aemConnectorSearchRequest.getQ() : "*";
        final MatchQueryMatch matchQueryMatch = new MatchQueryMatch();
        matchQueryMatch.text(fullTextSearchStr);
        final MatchQuery matchQuery = new MatchQuery();
        matchQuery.match(matchQueryMatch);
        polarisSearchRequest.addQueryItem(matchQuery);
    }
}
