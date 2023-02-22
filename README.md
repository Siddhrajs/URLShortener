# URLShortener

URL shortening is used to create shorter aliases for long URLs. We call these shortened aliases “short links.” Users are redirected to the original URL when they hit these short links. Short links save a lot of space when displayed, printed, messaged, or tweeted. Additionally, users are less
likely to mistype shorter URLs. Url shortener service is used to share shortened url via SMS or link via mail. URL shortening is used for optimizing links across devices, tracking individual links to analyze audience and campaign performance, and hiding affiliated original URLs.

In this project, I have created URLShortener - a service which will primarily shorten url for a given long
url and give long url for a short url.

## Requirements
This URL shortening system ensembles following requirements:
* Given a URL, this service will generate a unique, shorter alias of it, called as short URL.
* When users click on the short URL, this service will return corresponding long URL if it is existing or will give an error message, otherwise.
* Each short URL will get expire after a predefined amount of time and will be removed from the storage.
* Analytic report will get generated on the day-to-day basis.

## Technical Specifications
URLShortener service utilises following technologies for the development and proper functioning:
* Language - Java
* Framework - Spring Boot
* Database - MySQL
* Server - Apache Tomcat
* Cache - Redis
