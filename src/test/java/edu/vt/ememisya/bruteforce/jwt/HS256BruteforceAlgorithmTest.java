package edu.vt.ememisya.bruteforce.jwt;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import edu.vt.ememisya.bruteforce.BruteforceAlgorithm;
import edu.vt.ememisya.bruteforce.BruteforceAlgorithmImpl;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests bruteforcing a short password.
 *
 * @author Erdem Memisyazici
 */
public class HS256BruteforceAlgorithmTest {

    /**
     * An example of a really bad secret.
     */
    private static final String BAD_SECRET = "bad";

    /**
     * Tests HS256BruteforceAlgorithm.
     */
    @Test
    public void testBadHS256() {
        final String token = generateBadToken();
        //init bruteforcer
        TokenBruteforceAlgorithm jwtBruteforcer
                = new HS256BruteforceAlgorithm();
        //Decode token parts
        final DecodedJWT decoded = JWT.decode(token);
        //Begin bruteforcing
        final String foundSecret
                = jwtBruteforcer.bruteforce(
                        new BruteforceAlgorithmImpl(
                                BruteforceAlgorithm.LOWER_CASE_ALPHABET),
                        decoded.getHeader().getBytes(),
                        decoded.getPayload().getBytes(),
                        Base64.decodeBase64(decoded.getSignature()));
        assertEquals(BAD_SECRET, foundSecret);
        System.out.println(String.format("Secret is '%s'.", foundSecret));
    }

    /**
     * Generates an HS256 token with a short secret.
     *
     * @return Generated JWT token
     */
    private String generateBadToken() {
        final Algorithm algorithm;
        try {
            algorithm = Algorithm.HMAC256(BAD_SECRET);
        } catch (IllegalArgumentException | UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
        final String token = JWT.create().sign(algorithm);
        return token;
    }

}
