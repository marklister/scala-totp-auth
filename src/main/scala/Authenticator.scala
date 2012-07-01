
package org.catch22.totp.auth

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac
import scala.Array.canBuildFrom
import scala.math.pow

/**
 * Authenticator derives directly from the Reference Implementation
 */
object Authenticator {

  /**
   * Perform a basic test
   */
  def main(a: Array[String]): Unit = {
    println("Demo totp generation (google authenticator) see RFC 6238 http://tools.ietf.org/html/rfc6238")
    println
    val key: TOTPSecret = new TOTPSecret
    val t = System.currentTimeMillis / 30000
    println("Base32 secret: " + key.toBase32)
    println("Hex secret   : " + key.toString(16))
    println
    println("Time    : OTP")
    println("--------:--------")
    getTOTPSeq(key = key, window = 5).zipWithIndex.foreach { case (e, i) => println(i + t - 5 + ": " + e) }
    println
    println("Visit: http://google-authenticator.googlecode.com/git/libpam/totp.html to check results")
  }

  /**
   * This method generates a TOTP value for the given
   * set of parameters.
   *
   * @param key: the shared secret BigInt
   * @param time: a value that reflects a time (in 30 sec increments for google's implementation)
   * @param returnDigits: number of digits to return
   * @param crypto: the crypto function to use
   *
   * @return a numeric String in base 10.
   */

  def generateTOTP(key: BigInt, time: Long,
    returnDigits: Int, crypto: String): String = {

    val msg: Array[Byte] = BigInt(time).toByteArray.reverse.padTo(8, 0.toByte).reverse
    val hash = hmac_sha(crypto, key.toByteArray, msg)
    val offset: Int = hash(hash.length - 1) & 0xf
    val binary: Long = ((hash(offset) & 0x7f) << 24) |
      ((hash(offset + 1) & 0xff) << 16) |
      ((hash(offset + 2) & 0xff) << 8 |
        (hash(offset + 3) & 0xff))

    val otp: Long = binary % (pow(10, returnDigits)).toLong

    ("0" * returnDigits + otp.toString).takeRight(returnDigits)
  }

  /**
   * Generate a sequence of TP values. The idea is that there is no single
   * correct OTP value -- rather a group of OTPs that should be regarded as
   * correct depending on client clock drift network latency and the time taken
   * for the user to enter the otp.
   *
   * @param key: the shared secret BigInt
   * @param time: a value that reflects a time
   *     (in 30 sec increments for google's implementation) default current
   *     timestamp (google terminology)
   * @param returnDigits: number of digits to return
   * @param crypto: the crypto function to use
   * @param window: the window size to use.
   *
   * @return a sequence of numeric Strings in base 10
   */
  def getTOTPSeq(key: BigInt,
    time: Long = System.currentTimeMillis / 30000,
    returnDigits: Int = 6,
    crypto: String = "HmacSha1",
    window: Int = 3): Seq[String] = {
    (-window to window).foldLeft(Nil: Seq[String])((a, b) => generateTOTP(key, time + b, returnDigits, crypto) +: a).reverse

  }

  def hmac_sha(crypto: String, keyBytes: Array[Byte], text: Array[Byte]): Array[Byte] = {
    val hmac: Mac = Mac.getInstance(crypto)
    val macKey = new SecretKeySpec(keyBytes, "RAW")
    hmac.init(macKey)
    hmac.doFinal(text)
  }
}
