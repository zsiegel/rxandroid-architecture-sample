rxandroid-architecture-sample
=============================

A sample project to demonstrate an android app that can consume data from multiple sources using RxJava.

The basic project has 4 modules

- app (this is the android application)
- persistence (this is an android module that typically would have a content provider or even local data caching)
- api (this is a java module for communication with an API)
- core (this is a java module containing core domain classes)

Each module provides a specific set of functionality and exposes a Dagger module for access to their services.

At the application level we combine these modules and services and create a single service layer for the application to communicate with.

In this example the `UserService` that we use in our application is composed of both a `UserApiService` and a `UserPersistenceService`. The `UserService` is responsible for composing and serving the data based on the `DataRequest`. 

A `DataRequest` at the basic level allows the user to specify their source (local(persistnce), remote(api), or refresh(persistence and api)) and an ID to get a specific resource. This can be later extended for more querying and filtering as needed.