package org.catch22.totp
    /**
     * ==Introduction==
     * This package duplicates the functionality of the Reference Implementation
     * of RFC 6238.  The R.I. source is in the test sources and this package is tested against
     * the RI (rev 6).
     *
     * It was created to solve a problem converting Base32 and Hex using the existing libraries.
     * I think BigInt simplifies things considerably.
     *
     * ==Basic use==
     * Create a new secret:
     * {{{
     * val secret = new TOTPSecret
     * }}}
     *
     * Pass the secret to your user and store in your database.  You might want to generate a QR code.
     * Your user loads the secret into their portable device.
     *
     * When your user logs in you check to see if they generated the correct TOTP:
     * {{{val success= Authenticator.pinMatchesSecret(pin,usersSecret)
     * }}}
     * If the {{{usersSecet}}} is {{{None}}} and the pin is {{{None}}} returns true. Otherwise the pin
     * is checked against the 7 possible pins in the current window using Google Authenticator defaults. 
     *
     *
     * ==Conversions==
     * {{{    val b32:String = secret.toBase32                      Integer-> Base32
     *     val hex:String = secret.toString(16)                  Integer-> Hex
     *     val secret:TOTPSecret = TOTPSecret(base32String)      Base32 -> Integer
     *     val secret:TOTPSecret = TOTPSecret.fromHex(hexString) Hex    -> Integer
     *     val b:Array[Byte] = secret.toByteArray                Integer-> Array[Byte]}}}
     *
     *==Generating a totp to test==
     * you can use `oathtool` (available at least in current Debian repos)
     *
     * {{{oathtool --totp secret_in_HEX}}}
     * 77777777 base32 == ffffffffff Hex 
     */  

package object auth {
  
}
