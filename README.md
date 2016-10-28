# test_sierra
Test jdbc connection to Azure for MacOS Sierra

Don't forget to add `sqljdbc42.jar` to project dependencies

Add `-Djavax.net.debug=ssl:handshake:verbose` to your VM options or run `ExampleSQLJDBC` configuration

Fill in your config properties `~/test_sierra/src/main/resources/config.properties`, e.g.: 
```
HOST_NAME=testhost
DB_NAME=testdb
USER=test@test
PASSWORD=password
```
