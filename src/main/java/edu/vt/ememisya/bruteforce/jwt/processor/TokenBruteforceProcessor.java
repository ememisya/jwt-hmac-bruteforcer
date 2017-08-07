package edu.vt.ememisya.bruteforce.jwt.processor;

import java.io.IOException;
import java.io.OutputStream;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.commons.codec.binary.Base64;

import edu.vt.ememisya.bruteforce.BruteforceAlgorithm;
import edu.vt.ememisya.bruteforce.jwt.TokenBruteforceAlgorithm;

/**
 * Executes the bruteforce process for a given JWT token.
 *
 * @author Erdem Memisyazici
 */
public class TokenBruteforceProcessor {

    /**
     * Attempts to bruteforce a token with the supplied
     * {@link TokenBruteforceAlgorithm} using the underlying
     * {@link BruteforceAlgorithm} algorithm. Uses stdout for messages.
     *
     * @param decoded {@link DecodedJWT} token to bruteforce
     * @param tokenBruteforceAlgorithm {@link TokenBruteforceAlgorithm} instance
     * @param bruteforceAlgorithm {@link BruteforceAlgorithm} algorithm to use
     * in conjunction with tokenBruteforcer
     * @param output {@link OutputStream} instance for messages
     * @throws java.io.IOException If the {@link OutputStream} cannot be written
     * to.
     *
     */
    public void process(
            final DecodedJWT decoded,
            final TokenBruteforceAlgorithm tokenBruteforceAlgorithm,
            final BruteforceAlgorithm bruteforceAlgorithm,
            final OutputStream output) throws IOException {
        println(output, String.format(
                "Attempting to bruteforce JWT token ('%s')...",
                tokenBruteforceAlgorithm.getTokenAlgorithm()));
        println(output, decoded.getToken());
        //Begin bruteforcing
        long startTimestamp = System.currentTimeMillis();
        final String foundSecret
                = tokenBruteforceAlgorithm.bruteforce(
                        bruteforceAlgorithm,
                        decoded.getHeader().getBytes(),
                        decoded.getPayload().getBytes(),
                        Base64.decodeBase64(decoded.getSignature()));
        long endTimestamp = System.currentTimeMillis();
        println(output,
                String.format(
                        "Secret is '%s'. Time elapsed %ss",
                        foundSecret, (endTimestamp - startTimestamp) / 1000));
        output.flush();
        output.close();
    }

    /**
     * Helper method to print a line to an output stream.
     *
     * @param out
     * @param msg
     * @throws IOException
     */
    private void println(final OutputStream out, final String msg)
            throws IOException {
        out.write((msg + "\n").getBytes());
    }

}
