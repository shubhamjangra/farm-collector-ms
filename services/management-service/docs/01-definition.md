# Management Service Definition Document

Organization “FarmCollector” wants to collect information from Farmers for every field for every season, per farm (2
API):

1. Planted:   	
   a. Planting Area (in acres).
   b. Type of crops planted.
   c. Amount of Expected product (in tons)

2. Harvested:
   a. Actual amount of harvested product.
   Organization “FarmCollector” want to see reports for every season shows expected vs actual amount of product:
    1. For each farm
    2. For each Type of crops

For example Farmer from the farm “MyFarm” planted corn and potatoes. He will submit four sets of data – one for corn,
one for potato, where planted then one for corn , one for potatoes, where harvested.
Reports should be available on the web page for every season.

## Service API

### Synchronous APIs

| API Operation  | Events Published   |
|----------------|--------------------|
| createEntity() | EntityCreatedEvent |
| deleteEntity() | EntityDeletedEvent |
| getEntity()    | NA                 |

> In the above table replace API operations with your API operations

### Asynchronous APIs

| API Operation | Events Published |
|---------------|------------------|
| generateX()   | NA               |

## Non-functional Requirements

* Availability: xx.xx% availability. For example, 99.95% availability
* Throughput: x y/second. For example, 1000 transactions/second
* Performance: x ms per each y operation invocation. For example, 500 ms to create a customer

## Observability

### Key metrics

Following are the key business metrics that should be visible in our observability tool chain

* Number of successful events. Example, successful device registrations
* Number of failure events. Example, failed device registrations

### Health check URL

Service health check endpoint is located at /actuator/health

## Implementation

For implementation details refer to this [document](./02-design.md).

## Dependencies

List of downstream services this service depends on. These are both sync(invocations) and async(subscription)
dependencies.

* TODO
