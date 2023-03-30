# sprinklr-polaris-connector-poc
## Folder setup
- assets-search.yaml
- (empty folder) Java-Client
- consumers -> containing maven project to leverage Java-Client (auto-generated) to connect to Polaris SearchRequest. (Sync with artifact in git)

```
├── Java-Client
├── assets-search.yaml
└── consumers
    └── Polaris-Search-Swagger-Java-Client-Consumer
        ├── openapitools.json
        ├── pom.xml
        └── src
            ├── main
            │   ├── java
            │   │   └── ConsumerMainSwaggerPolarisSearch.java
            │   └── resources
```

            


## Steps
1. Java-Client folder should be empty.
2. run
> swagger-codegen generate -i assets-search.yaml -l java -o Java-Client
3. Temporary shortcut: Add javax.annotation dependency to have the generated client compile with Java 11
```
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.1</version>
</dependency>
```
4. Go to folder Java-Client
> cd Java-Client
5. Compile
> mvn clean install
6. Go to consumers folder -> Polaris-Search-Swagger-Java-Client-Consumer -> run the file ConsumerMainSwaggerPolarisSearch.java (has the `main` method)