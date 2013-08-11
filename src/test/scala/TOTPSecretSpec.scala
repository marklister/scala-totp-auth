package org.catch22.totp.auth
import org.specs2.mutable.Specification

  class TOTSecretSpec extends Specification {

  val sec= TOTPSecret()
    "Base32 String "+sec.toBase32 should {
      "contain 16 characters" in {
        sec.toBase32 must have size(16)
      }      
    }

    "Base32 String 77777777" should {
      "evaluate to ffffffffff hex" in {
        TOTPSecret("77777777").toString(16) must beEqualTo ("ffffffffff")
      }      
    }
    val ba=sec.toByteArray
    val sec2=TOTPSecret(ba)

    "Secret created from Array[Byte] should equal" should {
      "one created by other means" in {
        sec.toString(16) must beEqualTo (sec.toString(16))
      }   
    }
  }
