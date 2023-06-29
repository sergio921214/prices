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
3. Build the project: `gradle build`
4. Start the server: `gradle bootRun`
5. Access the API at `http://localhost:8080`

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

## Contact
If you have any questions, suggestions, or feedback regarding the prices repository or API, please feel free to reach out to us. You can contact the repository owner, Sergio, through the contact information provided in the repository's README file.

We hope you find the prices API useful for retrieving price data and integrating it into your Gradle projects. Enjoy exploring and utilizing the endpoint!
