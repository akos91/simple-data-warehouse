# Simple Data Warehouse

At startup the application reads the provided csv file and loads it into an in-memory database which can be queried via REST API.

Deployed to AWS. The API can be reached on the following URL: http://ec2-18-218-141-136.us-east-2.compute.amazonaws.com:8080/advertisement/stats

## REST API usage
There's one API end point to retrieve queries on the advertisement data.
The Content-Type is application/json

URL: {host}:8080/advertisement/stats

Verb: POST

### Request body 
```
{
    "metrics": [ "CLICK", "IMPRESSION", "CTR" ],
    "dimensions": ["DATASOURCE", "DAILY"],
    "filters": {"DATASOURCE":"Google Ads,Twitter Ads", "FROM_DATE":"2019-11-10", "TO_DATE":"2019-11-13"}
}
```
#### Metrics list
Parameter: metrics

Possible values: CLICK, IMPRESSION, CTR

#### Dimensions list
Parameter: dimensions

Possible values: DATASOURCE, CAMPAIGN, DAILY

Optional parameter

#### Filters map
Parameter: filters

Possible values: DATASOURCE, CAMPAIGN, FROM_DATE, TO_DATE

Optional parameter

### Response
The response object contains two list:
- dimensions
- metrics

HTTP status code: 200

In case of an invalid request an error message retrieved with HTTP 400.

```
{
    "advertisementStats": [
        {
            "dimensions": {
                "DATASOURCE": "Google Ads",
                "DAILY": "2019-11-12"
            },
            "metrics": {
                "CLICK": 166.0000,
                "IMPRESSION": 106476.0000,
                "CTR": 0.1500
            }
        },
        {
            "dimensions": {
                "DATASOURCE": "Google Ads",
                "DAILY": "2019-11-11"
            },
            "metrics": {
                "CLICK": 151.0000,
                "IMPRESSION": 76172.0000,
                "CTR": 0.1900
            }
        },
        {
            "dimensions": {
                "DATASOURCE": "Twitter Ads",
                "DAILY": "2019-11-12"
            },
            "metrics": {
                "CLICK": 8594.0000,
                "IMPRESSION": 99200.0000,
                "CTR": 8.6600
            }
        },
        {
            "dimensions": {
                "DATASOURCE": "Twitter Ads",
                "DAILY": "2019-11-11"
            },
            "metrics": {
                "CLICK": 8563.0000,
                "IMPRESSION": 57177.0000,
                "CTR": 14.9700
            }
        }
    ]
}
```

## Example
Simple_Data_Warehouse.postman_collection can be found in the project folder with some calls.
