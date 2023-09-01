# Contact book Android app

## App configuration
- Gradle 8.2
- Java 17
- Kotlin 1.9
- Compose compiler 1.5.2

## App structure
    | app
    | features
        | common : common components
        | userdetails : user details feature
        | userlist : user list feature
    | network
        | retrofit-impl : retrofit implementations of user api
        | user-api : user api to get users from network
    | plugins 
        custom gradle plugins conventions to reuse in modules
    | storage
        | datastore-impl : proto datastore implementation of user api
        | user-api : user api to get users from local storage

## Libraries
- Compose UI & navigation with an extended MVI pattern
- DI with Koin
- Networking with Retrofit & OkHttp
- Json converter with Moshi
- Loading image with Coil
- Storing with datastore by protobuf
- Streams with coroutines & flow
- Testing with JUnit 5
- Mocking with Mockk
- Coroutines test with Turbine & Kotest syntax
