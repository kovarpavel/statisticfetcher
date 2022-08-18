# Statistic fetcher API
This is simple implementation of given task to provide language statistics from org's repositories.
To get response from localhost use: `curl --request GET --url http://localhost:8080/statistic`

### Build and run
To build and run tests: `./gradlew clean build`
To run application: `./gradlew bootRun`

### Implementation notes

- For gathering data, I choose REST API
- For API calls to Github, I used Spring WebClient which is by default reactive, but for keep it simple I blocked calls
- For persistence, I used h2 in-memory database to make local run easier
- For testing, I just used JUnit and Mockito. There are other options as Kotest

