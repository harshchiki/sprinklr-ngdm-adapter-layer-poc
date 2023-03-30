# MatchQueryMatch

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**fields** | **List&lt;String&gt;** | fields to search text in (optional) |  [optional]
**text** | **String** | the text to search. An empty string searches for everything.  | 
**operator** | [**OperatorEnum**](#OperatorEnum) | Defines how to interpret the &#x60;text&#x60; input * &#x60;contains&#x60; : Give all results where string has anything before and after the string text: *text* * &#x60;startsWith&#x60;: Give all results where string has anything staring with text: text* * &#x60;endsWith&#x60;: Give all results where string has anything ending with text: *text * &#x60;fuzzy&#x60;: Give fuzzy search results (edit distance - 1 or 2) with string text and some spelling mistakes are permissible : text~  |  [optional]

<a name="OperatorEnum"></a>
## Enum: OperatorEnum
Name | Value
---- | -----
CONTAINS | &quot;contains&quot;
STARTSWITH | &quot;startsWith&quot;
ENDSWITH | &quot;endsWith&quot;
FUZZY | &quot;fuzzy&quot;
