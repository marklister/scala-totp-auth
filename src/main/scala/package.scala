package org.catch22.totp
    /**
     * ==Introduction==
     * This package duplicates the functionality of the Reference Implementation
     * of RFC 6238.  The R.I. source is in the test sources and this package is tested against
     * the RI (rev 6).
     *
     * It was created to solve a problem converting Base32 and Hex using the existing libraries.
     *
     * ==Basic use==
     * Create a new secret:
     * {{{
     * val secret = TOTPSecret()
     * }}}
     *
     * Pass the secret to your user.  You might want to generate a QR code.
     * Your user loads the secret into their device (Android/ IOS/ J2ME ...).
     *
     * When your user logs in you check to see if they generated the correct TOTP:
     * {{{val possibleOTPs = Authenticator.getTOTPSeq(secret=usersSecret)
     *        if(!possibleOTPs.contains(userEnteredOTP))  loginFailed()
     *        
     *        /*Login failed.   Don't return a message indicating the exact nature of 
     *        the failure as this may expose the password to a brute force attack*/
     * }}}
     * 
     * ==Note== 
     * 
     * Secrets generated are guaranteed to be n digits long.  Some keyspace is
     * sacrificed. See the project issue tracker for a discussion. 
     *
     * ==Time Drift==
     *
     * Check your user's clock and warn them if they are 'on the limit':
     * {{{ val drift = possibleOTPs.indexOf(userEnteredOTP) - window //window is 3 by default
     *     // a value of zero is ideal.  A value approaching -window or +window indicates excessive
     *     // drift and you should inform the user.}}}
     *
     *
     * ==Conversions==
     * {{{    val b32:String = secret.toBase32                      Integer-> Base32
     *     val hex:String = secret.toString(16)                  Integer-> Hex
     *     val secret:TOTPSecret = TOTPSecret(base32String)      Base32 -> Integer
     *     val secret:TOTPSecret = TOTPSecret.fromHex(hexString) Hex    -> Integer
     *     val b:Array[Byte] = secret.toByteArray                Integer-> Array[Byte]}}}
     *
     *
     */  

package object auth {
  
}
