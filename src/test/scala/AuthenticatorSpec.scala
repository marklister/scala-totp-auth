package org.catch22.totp.auth
import org.itef.totp.TOTP
import org.specs2.mutable.Specification

  class AuthenticatorSpec extends Specification {

//sequential

//"Base32 String "+sec.toBase32 should {
//      "produce the same otp as the java RI" in {
//        Authenticator.generateTOTP(sec,99,6,"HmacSha1") must beEqualTo(TOTP.generateTOTP(sec.toString(16),"00000000000000099","6","HmacSha1"))
//      }      
//    }

def pad(s:String,n:Int):String={
  ("0"*n +n.toString).takeRight(n)
}

  val sec= TOTPSecret.fromHex("3132333435363738393031323334353637383930")
    "Hex String '3132333435363738393031323334353637383930' ("+sec.toBase32+")" should {
      "produce the same otp as line 1 of table 1 in the Ref. Implementation" in {
        Authenticator.totp(sec,1,8,"HmacSHA1") must beEqualTo("94287082")
      }       
    }

    "RI-Test Hex String '3132333435363738393031323334353637383930' ("+sec.toBase32+")"  should {
      "produce the same otp as line 1 of table 1 in the Ref. Implementation" in {
        TOTP.generateTOTP(sec.toString(16),"0000000000000001","8","HmacSHA1") must beEqualTo("94287082")
      }       
    }

//Technically we should use different keys for each method mapped to the mode.  But not gonna...
val times=List(59,1111111109,1111111111,1234567890,2000000000 )
val modes=List("HmacSHA1","HmacSHA256","HmacSHA512")
for (t<-times){
  for (m<-modes){
    "Time "+t +" mode:"+m should {
      "produce the same result for both implementations" in {
        TOTP.generateTOTP(sec.toString(16),("0" * 16 +(t/30).toHexString).takeRight(16),"8",m) must beEqualTo(
         Authenticator.totp(sec,t/30,8,m) )
      }
    }
  }
}

for (t<-times){
  for (m<-modes){
    val k=TOTPSecret()
    "Time "+t +" mode:"+m + " key(hex):"+k.toString(16)+" B32:"+k.toBase32 should {
      "produce the same result for both implementations (random key)" in {
        TOTP.generateTOTP(k.toString(16),("0" * 16 +(t/30).toHexString).takeRight(16),"8",m) must beEqualTo(
         Authenticator.totp(k,t/30,8,m) )
      }
    }
  }
}


"pin of None" should {
      "produce true for a match if the secret is None" in {
         Authenticator.pinMatchesSecret (None,None) must beTrue
      }
    }

"Pin with something in it" should {
      "produce false for a match if the secret is None" in {
         Authenticator.pinMatchesSecret (Some("123456"),None) must beFalse
      }
    }

  }
