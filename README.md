# EBSimpleAPI

Maven/Spring Boot application which exposes a REST XML API to query topic information. The default "dev" profile
retrieves data from an in-memory XML file, but this can be generalized to any other persistence store(database,web service, etc)
by providing another implementation of the TopicQueryRepository.

This project can be run locally using the Maven command 'mvn spring-boot:run' or the standard 'mvn package' command can be
used to produce a WAR file that can be deployed in any compatible Java servlet container.

This project also contains a file at the root (EBSimpleAPI.postman_collection.json) of the exported Postman project which can be used to client API testing (Spring default dev profile will test against in-memory XML file)
