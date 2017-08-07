package edu.vt.ememisya.bruteforce.jwt;

import edu.vt.ememisya.bruteforce.BruteforceAlgorithm;

/**
 * Contract defining JWT bruteforce algorithms with common methods.
 *
 * @author Erdem Memisyazici
 */
public interface TokenBruteforceAlgorithm {

    /**
     * Returns the signature algorithm of this token.
     *
     * @return Algorithm identifier
     */
    String getTokenAlgorithm();

    /**
     * Runs the bruteforcing attempt and returns the found secret when complete.
     *
     * @param bruteForcer {@link BruteforceAlgorithm} instance
     * @param algorithmHeader Algorithm header B64
     * @param payload Payload B64
     * @param comparisonSignature Signature Raw
     * @return Found secret
     */
    String bruteforce(
            final BruteforceAlgorithm bruteForcer,
            final byte[] algorithmHeader,
            final byte[] payload,
            final byte[] comparisonSignature);

    /**
     * Signs a JWT Token and returns the signature
     *
     * @param secret Secret to sign with
     * @param jwtHeader B64 JWT header
     * @param jwtPayload B64 JWT payload
     * @return Sign and return signature in raw bytes
     */
    byte[] signToken(
            final byte[] secret,
            final byte[] jwtHeader,
            final byte[] jwtPayload);

    /**
     * Prepares JWT token parts to be signed as defined by RFC 7519
     * https://tools.ietf.org/html/rfc7519
     *
     * @param jwtHeader B64 Header
     * @param jwtPayload B64 Payload
     * @return Data ready to sign.
     */
    public static byte[] prepareSignatureData(
            final byte[] jwtHeader,
            final byte[] jwtPayload
    ) {
        final byte[] d = ".".getBytes();
        final byte[] c
                = new byte[jwtHeader.length + jwtPayload.length + d.length];
        System.arraycopy(jwtHeader, 0, c, 0, jwtHeader.length);
        System.arraycopy(d, 0, c, jwtHeader.length, d.length);
        System.arraycopy(
                jwtPayload,
                0, c, jwtHeader.length + d.length, jwtPayload.length);
        return c;
    }

}
