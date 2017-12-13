## Service for user

In this challenge a member service is implemented. Project contains the unit tests so you can understand the endpoints and others by following unit tests.

Please note that: according to `Accept` and `Content-Type` header in your request, app will decide which kind of data you want to retrieve.

## Technology Used
* Spring Boot
* JUnit(Mockito and MockMvc)
* JPA
* H2
* Gradle

## Endpoint Description
* Here are the endpoints by following `/member`:
* GET : `/{memberId}` fetches a single member.
* GET : `/all` fetches all members.
* POST : `/` create a new user.
* PUT : `/` update a user. Send body with an ID.
* DELETE : `/{memberId}` delete a user. 

## Sample Bodies
### JSON
``` json
{"id":1,"firstName":"UserName","lastName":"LastName","dateOfBirth":"26-11-1987 09:34:29"}
```

``` json
[
{"id":1,"firstName":"UserName","lastName":"LastName","dateOfBirth":"26-11-1987 09:34:29"},
{"id":1,"firstName":"UserName","lastName":"LastName","dateOfBirth":"26-11-1987 09:34:29"}
]
```

### XML
```xml
<member>
  <id>1</id>
  <firstName>UserName</firstName>
  <lastName>LastName</lastName>
  <dateOfBirth>26-11-1987 09:34:29</dateOfBirth>
</member>
```
```xml
<Set>
  <item>
    <id>1</id>
    <firstName>UserName</firstName>
    <lastName>LastName</lastName>
    <dateOfBirth>26-11-1987 09:34:29</dateOfBirth>
  </item>
  <item>
    <id>1</id>
    <firstName>UserName</firstName>
    <lastName>LastName</lastName>
    <dateOfBirth>26-11-1987 09:34:29</dateOfBirth>
  </item>
</Set>
```



## How to Test
* Run `gradle test --info`
* Or just make a requests to the enpoints which i mentioned above. Before doing that run the server with command `gradle bootRun`. It will start to server on port `8080`.
* You can also import postman tests under test folder and test with postman.