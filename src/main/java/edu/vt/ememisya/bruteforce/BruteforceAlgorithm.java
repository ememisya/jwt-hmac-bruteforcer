package edu.vt.ememisya.bruteforce;

import java.util.function.Function;

/**
 * Contract for bruteforcing algorithms.
 *
 * @author Erdem Memisyazici
 */
public interface BruteforceAlgorithm {

    String LOWER_CASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    String NUMERIC_ALPHABET = "0123456789";
    String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Bruteforces and applies each iteration of guesses to checkCondition
     * function supplied and compares is against the secret data for success.
     *
     * @param secret secret data to compare against for success
     * @param checkCondition Function to apply the iteration logic to compare
     * incremental checks.
     * @return Found secret
     */
    byte[] bruteforce(
            final byte[] secret,
            final Function<byte[], byte[]> checkCondition);

}
