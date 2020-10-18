# dynamodb-quickstart project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Creating the table

Use the following javascript code in the dynamodb shell locally (or the equivalent on AWS) to create the necessary table:

    var params = {
    	TableName: "QuarkusBook",
    	KeySchema: [{AttributeName: "isbn", KeyType: "HASH"},
    				{AttributeName: "author", KeyType: "RANGE"}],
    	AttributeDefinitions: [{AttributeName: "isbn", AttributeType: "S"},
    							{AttributeName: "author", AttributeType: "S"}],
    	ProvisionedThroughput: {ReadCapacityUnits: 1, WriteCapacityUnits: 1}
    };						

    dynamodb.createTable(params, function(err, data) {
        if (err) ppJson(err);
        else ppJson(data);
    });

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `dynamodb-quickstart-1.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/dynamodb-quickstart-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/dynamodb-quickstart-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .
