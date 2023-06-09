/*
 * Activated Assets' Search API
 * API definition for Activated Assets' Search Capabilities
 *
 * OpenAPI spec version: 0.2.0-experimental
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.CompositeQuery;
import io.swagger.client.model.Sortable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * SearchRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-03-30T19:07:33.419517+05:30[Asia/Kolkata]")
public class SearchRequest {
  @SerializedName("query")
  private List<CompositeQuery> query = new ArrayList<CompositeQuery>();

  @SerializedName("sort")
  private List<Sortable> sort = null;

  @SerializedName("projectedFields")
  private List<String> projectedFields = null;

  @SerializedName("limit")
  private Integer limit = 50;

  @SerializedName("offset")
  private Integer offset = 0;

  public SearchRequest query(List<CompositeQuery> query) {
    this.query = query;
    return this;
  }

  public SearchRequest addQueryItem(CompositeQuery queryItem) {
    this.query.add(queryItem);
    return this;
  }

   /**
   * Get query
   * @return query
  **/
  @Schema(required = true, description = "")
  public List<CompositeQuery> getQuery() {
    return query;
  }

  public void setQuery(List<CompositeQuery> query) {
    this.query = query;
  }

  public SearchRequest sort(List<Sortable> sort) {
    this.sort = sort;
    return this;
  }

  public SearchRequest addSortItem(Sortable sortItem) {
    if (this.sort == null) {
      this.sort = new ArrayList<Sortable>();
    }
    this.sort.add(sortItem);
    return this;
  }

   /**
   * Sort attributes are applied to the query results in the order they are present e.g. &#x60;&#x60;&#x60; \&quot;sort\&quot;: [   {     \&quot;by\&quot;: \&quot;metadata.size\&quot;,     \&quot;order\&quot;: \&quot;desc\&quot;   },   {     \&quot;by\&quot;: \&quot;metadata.repository.repo:createDate\&quot;,     \&quot;order\&quot;: \&quot;asc\&quot;   } ] &#x60;&#x60;&#x60; will return results ordered first by size and then by format 
   * @return sort
  **/
  @Schema(description = "Sort attributes are applied to the query results in the order they are present e.g. ``` \"sort\": [   {     \"by\": \"metadata.size\",     \"order\": \"desc\"   },   {     \"by\": \"metadata.repository.repo:createDate\",     \"order\": \"asc\"   } ] ``` will return results ordered first by size and then by format ")
  public List<Sortable> getSort() {
    return sort;
  }

  public void setSort(List<Sortable> sort) {
    this.sort = sort;
  }

  public SearchRequest projectedFields(List<String> projectedFields) {
    this.projectedFields = projectedFields;
    return this;
  }

  public SearchRequest addProjectedFieldsItem(String projectedFieldsItem) {
    if (this.projectedFields == null) {
      this.projectedFields = new ArrayList<String>();
    }
    this.projectedFields.add(projectedFieldsItem);
    return this;
  }

   /**
   * List of names of fields to be present in the search result (Optional, &#x27;all&#x27; if not specified)
   * @return projectedFields
  **/
  @Schema(description = "List of names of fields to be present in the search result (Optional, 'all' if not specified)")
  public List<String> getProjectedFields() {
    return projectedFields;
  }

  public void setProjectedFields(List<String> projectedFields) {
    this.projectedFields = projectedFields;
  }

  public SearchRequest limit(Integer limit) {
    this.limit = limit;
    return this;
  }

   /**
   * Maximum number of results to be returned
   * minimum: 1
   * maximum: 100
   * @return limit
  **/
  @Schema(description = "Maximum number of results to be returned")
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public SearchRequest offset(Integer offset) {
    this.offset = offset;
    return this;
  }

   /**
   * The number of records that need to be skipped
   * minimum: 0
   * maximum: 10000
   * @return offset
  **/
  @Schema(description = "The number of records that need to be skipped")
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchRequest searchRequest = (SearchRequest) o;
    return Objects.equals(this.query, searchRequest.query) &&
        Objects.equals(this.sort, searchRequest.sort) &&
        Objects.equals(this.projectedFields, searchRequest.projectedFields) &&
        Objects.equals(this.limit, searchRequest.limit) &&
        Objects.equals(this.offset, searchRequest.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(query, sort, projectedFields, limit, offset);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchRequest {\n");
    
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    projectedFields: ").append(toIndentedString(projectedFields)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
