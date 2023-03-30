# SearchRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**query** | [**List&lt;CompositeQuery&gt;**](CompositeQuery.md) |  | 
**sort** | [**List&lt;Sortable&gt;**](Sortable.md) | Sort attributes are applied to the query results in the order they are present e.g. &#x60;&#x60;&#x60; \&quot;sort\&quot;: [   {     \&quot;by\&quot;: \&quot;metadata.size\&quot;,     \&quot;order\&quot;: \&quot;desc\&quot;   },   {     \&quot;by\&quot;: \&quot;metadata.repository.repo:createDate\&quot;,     \&quot;order\&quot;: \&quot;asc\&quot;   } ] &#x60;&#x60;&#x60; will return results ordered first by size and then by format  |  [optional]
**projectedFields** | **List&lt;String&gt;** | List of names of fields to be present in the search result (Optional, &#x27;all&#x27; if not specified) |  [optional]
**limit** | **Integer** | Maximum number of results to be returned |  [optional]
**offset** | **Integer** | The number of records that need to be skipped |  [optional]
