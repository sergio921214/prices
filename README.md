# prices

Welcome to the **prices** repository!

This repository contains a Gradle project that provides an API for retrieving price data for various products. The API offers a single endpoint to fetch price information based on specific criteria.

## Endpoint

### Get Price Data

- Endpoint: `/prices`
- Method: GET
- Parameters:
  - `product`: Filter prices by product name.
  - `category`: Filter prices by category.
  - `date`: Filter prices by date and time.
- Response:
  - Status Code: 200 (OK)
  - Body: JSON array containing price objects with the following properties:
    - `productId`: ID of the product.
    - `brandId`: ID of the brand.
    - `priceList`: rate of the product.
    - `startDate`: Date and time when starts the price data.
    - `endDate`: Date and time when ends the price data.
    - `price`: Price value.

## Setup and Usage

To set up and use the **prices** API, follow these steps:

1. Clone the repository: `git clone https://github.com/sergio921214/prices.git`
2. Navigate to the project directory: `cd prices`
3. Build the project: `gradlew build`
4. Start the server: `gradlew bootRun`
5. Access the API at `http://localhost:8080`
6. Execute JacocoTestReport analysis: `gradlew jacocoTestReport`
7. Check sonar code analysys refer: `https://sonarcloud.io/summary/overall?id=sergio921214_prices`

## Example

GET /prices?product=example-product&category=example-category&date=2023-06-28T00:00:00

## Get Prices


## Data Format
The price data is stored in a relational database, such as MySQL or PostgreSQL, and accessed using an ORM like Hibernate. Each price entry consists of the following fields:

- `BRAND_ID`: Foreign key of the brand group (1 = ZARA).
- `START_DATE`: Start date of the indicated price rate.
- `END_DATE`: End date of the indicated price rate.
- `PRICE_LIST`: Identifier of the applicable price list.
- `PRODUCT_ID`: Product code identifier.
- `PRIORITY`: Price application disambiguator. If two price lists overlap in a date range, the one with the higher priority (higher numeric value) is applied.
- `PRICE`: Final selling price.
- `CURR`: ISO currency code.

## Test scenarios defined by the technical exam

NOTE: Below you can find the test scenarios required by the technical examen related to the test names in the PriceControllerIntegrationTest class

-  Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA) => getPrice_duringStandardDateTimePrice_thenReturnsPriceResponse()
-  Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA) => getPrice_duringDateTimeWith30PercentDiscount_thenReturnsPriceResponse()
-  Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA) => getPrice_duringDateTimeWithoutDiscount_thenReturnsPriceResponse()
-  Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA) => getPrice_duringDateTimeWith15PercentDiscount_thenReturnsPriceResponse()
-  Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA) => getPrice_duringDateTimeWith10PercentSurcharge_thenReturnsPriceResponse()

## Unit testing

- Additional unit tests added to the service class of the project : `PriceServiceTest`.
- Those unit tests were designed using TestDataBuilder classes to build dummy objects for testing purposes : `PriceEntityDataBuilder`,`PriceResponseDataBuilder`
  
## Contact
If you have any questions, suggestions, or feedback regarding the prices repository or API, please feel free to reach out to us. You can contact the repository owner, Sergio, through the contact information provided in the repository's README file.

We hope you find the prices API useful for retrieving price data and integrating it into your Gradle projects. Enjoy exploring and utilizing the endpoint!
