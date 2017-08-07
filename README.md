## JWT HMAC Bruteforcer

A simple Java application written to demonstrate bruteforcing JWT tokens
using an insecure secret with an HMAC algorithm.

Uses com.auth0.java-jwt library for parsing JWT tokens.

### v0.1

 * HS256 simple bruteforcing

_Sample Output:_

```
#java -jar jwt-hmac-bruteforcer-0.1-jar-with-dependencies.jar eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJobWFjYXdlc29tZSEifQ.NKioJb2zs1WQpLAZHQd6_zoul-_k4JJFMExQRvKOf0M

Attempting to bruteforce JWT token ('HS256')...
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJobWFjYXdlc29tZSEifQ.NKioJb2zs1WQpLAZHQd6_zoul-_k4JJFMExQRvKOf0M
Secret is 'passy'. Time elapsed 39s
```