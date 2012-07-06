###scala-totp-auth

This is a scala object that implements RFC 6238 time based one time passwords.
The HOTP protocol is identical except that timeing is not used to select OTPs.

###API

Take a look at the [scaladoc](./tree/master/target/scala-2.9.2/api/org/catch22/totp/auth/package.html) 

###Running

Check out the project 

Type `sbt run` 

###Test

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

###How to use it in my project?

for now there's only an [unmanaged jar](./tree/master/target/scala-2.9.2/scala-totp-auth_2.9.2-1.01.jar) or build it from source.
