/**
 *Copyright (c) 2011 IETF Trust and the persons identified as
 *authors of the code. All rights reserved.
 *
 *Redistribution and use in source and binary forms, with or without
 *modification, is permitted pursuant to, and subject to the license
 *terms contained in, the Simplified BSD License set forth in Section
 *4.c of the IETF Trust's Legal Provisions Relating to IETF Documents
 *(http://trustee.ietf.org/license-info).
 *
 * @author Johan Rydell, PortWise, Inc.
 * @author Mark Lister.
 */

package org.catch22.totp.auth
import java.security.SecureRandom
import scala.Array.canBuildFrom
import scala.math.BigInt.int2bigInt
import scala.math.BigInt
import scala.util.Random

/** The secret is represented as a BigInt.
 *  BigInts make it easy to convert between Hex and Base32.
 *  Method toByteArray lets you interface with standard crypto.
 *  Base32 is the defacto format used by Google authenticator.  The OAuthTool uses hex :(
 */

class TOTPSecret(bigInteger: java.math.BigInteger) extends BigInt(bigInteger) {

  /**
   * Create a new TOTP secret key of b32Digits * 5 bits in length using a custom random source 
   */
  def this(b32Digits: Int, r: Random) =
    this((2 to b32Digits).foldLeft(BigInt(r.nextInt(31)) + 1: BigInt)((a, b) => a * 32 + r.nextInt(32)).underlying)

  /**
   * Create a new TOTP secret key from an Array[Byte] 
   */
  def this(bytes: Array[Byte]) =
    this(new java.math.BigInteger(bytes))

    /**
   * Create a new TOTP secret key of b32Digits * 5 bits in length using the default random source
   */
  def this(b32Digits: Int) = this(b32Digits, new Random(new SecureRandom))

  /**
   * Create a new TOTP secret key 80 bits (16 Base 32 char) long using the default random source
   */
  def this() = this(16)

  private val B32 = ('A' to 'Z') ++ ('2' to '7')
  
  /**Convert this secret into its Base 32 string representation.
   */
  def toBase32: String = new String(this.toString(32).toCharArray.map(_.asDigit).map(B32(_)))

  /** A replacement of java.math.BigInteger.toByteArray as the parent method adds a zero
   *  byte to the head of the byte array from time to time.
   */
  override def toByteArray: Array[Byte] = {
    //Sometimes we have an extra zero byte to start :(
    val b = this.underlying.toByteArray
    if (b(0) == 0) b.tail else b
  }
}

object TOTPSecret {
  
  private val B32 = ('A' to 'Z') ++ ('2' to '7')
  /** Create a secret from a Base 32 String
   *  No error checking.
   */
  def apply(base32: String): TOTPSecret = {
    new TOTPSecret(base32.toUpperCase.map(B32.indexOf(_)).foldLeft(0: BigInt)((a, b) => a * 32 + b).underlying)
  }
  
  private val HEX=('0' to '9') ++ ('a' to 'f')
  /**Create a secret from a hex String
   * No error checking.
   */
  def fromHex(hex: String): TOTPSecret = {
    new TOTPSecret(hex.toLowerCase.map(HEX.indexOf(_)).foldLeft(0: BigInt)((a, b) => a * 16 + b).underlying)
  }
}
