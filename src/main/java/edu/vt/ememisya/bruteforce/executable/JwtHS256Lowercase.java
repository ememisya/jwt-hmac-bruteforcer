package edu.vt.ememisya.bruteforce.executable;

import java.io.IOException;

import com.auth0.jwt.JWT;

import edu.vt.ememisya.bruteforce.BruteforceAlgorithm;
import edu.vt.ememisya.bruteforce.BruteforceAlgorithmImpl;
import edu.vt.ememisya.bruteforce.jwt.HS256BruteforceAlgorithm;
import edu.vt.ememisya.bruteforce.jwt.processor.TokenBruteforceProcessor;
import edu.vt.ememisya.bruteforce.jwt.TokenBruteforceAlgorithm;

/**
 * An executable class to demonstrate bruteforcing HS256 JWT tokens with
 * lowercase secrets.
 *
 * @author Erdem Memisyazici
 */
public class JwtHS256Lowercase {

    /**
     * Executable class. Takes a single argument which is the HS256 JWT Token.
     *
     * @param args [0] Raw HS256 Token String
     * @throws java.io.IOException If System.out cannot be written to.
     */
    public static void main(final String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Please provide the JWT token as the only argument.");
        }
        //init hs256 bruteforcer
        final TokenBruteforceAlgorithm jwtBruteforcer
                = new HS256BruteforceAlgorithm();
        //init lowercase bruteforcer algorithm
        final BruteforceAlgorithm bruteforcer
                = new BruteforceAlgorithmImpl(
                        BruteforceAlgorithm.LOWER_CASE_ALPHABET);
        final TokenBruteforceProcessor tokenProcessor
                = new TokenBruteforceProcessor();
        final String token = args[0];
        tokenProcessor.process(JWT.decode(token),
                jwtBruteforcer, bruteforcer, System.out);
    }

}
