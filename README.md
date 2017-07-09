### scala-totp-auth

[![Build Status](https://travis-ci.org/marklister/scala-totp-auth.png)](https://travis-ci.org/marklister/scala-totp-auth)

This is a scala object that implements RFC 6238 time based one time passwords.
The HOTP protocol is identical except that timing is not used to select OTPs.
*scala-totp-auth* exists to make it easy to integrate totp into one of my Play 2.0 applications.  Target portable device runs Google Authenticator.

### Sample login code

```scala
//Retrieve the user's secret from the db
//retrieve the pin that the user entered from their device

val ok =Authenticator.pinMatchesSecret(userPin,TOTPSecret(base32Secret))
...
```

### API

Take a look at the [scaladoc](http://marklister.github.com/scala-totp-auth/latest/api/index.html#org.catch22.totp.auth.package) 

### Running

Check out the project 

Type `sbt run` 

### Reference Implementation

The Refererence Implementation is in the test sources.  *scala-totp-auth*'s behaviour should exactly match the RI.  There are a few tests that check this.

If you find any differences please file an issue.

If you think some behaviour should be different from the RI be prepared to motivate your proposal very well.

### Test

`sbt test`



    [info] Running Authenticator 
    
    http://google-authenticator.googlecode.com/git/libpam/totp.html to check results
    
    Base32 secret:RM26TUEIDMURGOPL Hex secret:8b35e9d0881b291339eb
    
    Time    : OTP
    --------:--------
    44699054: 680139
    44699055: 956581
    44699056: 553109
    44699057: 338225
    44699058: 450979
    44699059: 735724
    44699060: 027563
    44699061: 941682
    44699062: 338183
    44699063: 799711
    44699064: 061042

### How to use it in my project?

There's an 
  - [unmanaged jar 2.9](https://marklister.github.com/scala-totp-auth/scala-totp-auth_2.9.2-1.01.jar) 
  - [unmanaged jar for 2.10](https://marklister.github.com/scala-totp-auth/scala-totp-auth_2.10-1.1.jar)

Or build it from source.  There is only one class and two objects.
