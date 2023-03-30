# ActivatedAssetsSearchApi

All URIs are relative to *https://delivery-p{programId}-e{environmentId}.adobeaemcloud.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**search**](ActivatedAssetsSearchApi.md#search) | **POST** /adobe/assets/search | Search for activated assets based on specified criteria

<a name="search"></a>
# **search**
> SearchResponse search(body)

Search for activated assets based on specified criteria

Search Activated Assets

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ActivatedAssetsSearchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: ApiKey
ApiKeyAuth ApiKey = (ApiKeyAuth) defaultClient.getAuthentication("ApiKey");
ApiKey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//ApiKey.setApiKeyPrefix("Token");


ActivatedAssetsSearchApi apiInstance = new ActivatedAssetsSearchApi();
SearchRequest body = new SearchRequest(); // SearchRequest | 
try {
    SearchResponse result = apiInstance.search(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ActivatedAssetsSearchApi#search");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SearchRequest**](SearchRequest.md)|  |

### Return type

[**SearchResponse**](SearchResponse.md)

### Authorization

[ApiKey](../README.md#ApiKey)[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, application/problem+json

