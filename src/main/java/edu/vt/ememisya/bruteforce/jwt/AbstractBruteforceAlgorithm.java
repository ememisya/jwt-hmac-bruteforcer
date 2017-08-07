package edu.vt.ememisya.bruteforce.jwt;

import edu.vt.ememisya.bruteforce.BruteforceAlgorithm;

/**
 * Simple abstract implementation of {@link TokenBruteforceAlgorithm}.
 *
 * @author Erdem Memisyazici
 */
abstract public class AbstractBruteforceAlgorithm
        implements TokenBruteforceAlgorithm {

    /**
     * Runs the {@link BruteforceAlgorithm} implementation given to match the
     * resulting signature from
     * {@link TokenBruteforceAlgorithm#signToken(byte[], byte[], byte[])}
     *
     * @param bruteForcer {@link BruteforceAlgorithm} instance
     * @param algorithmHeader Algorithm header B64
     * @param payload Payload B64
     * @param comparisonSignature Raw signature bytes to compare as final result
     * @return Found secret
     */
    @Override
    public final String bruteforce(
            final BruteforceAlgorithm bruteForcer,
            final byte[] algorithmHeader,
            final byte[] payload,
            final byte[] comparisonSignature
    ) {
        return new String(bruteForcer.bruteforce(comparisonSignature,
                (byte[] t) -> signToken(t, algorithmHeader, payload)));
    }

}
