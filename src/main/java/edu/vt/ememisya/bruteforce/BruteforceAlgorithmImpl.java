package edu.vt.ememisya.bruteforce;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Modified from https://github.com/a11n/bruteforce
 *
 * @author Erdem Memisyazici
 */
public final class BruteforceAlgorithmImpl implements BruteforceAlgorithm {

    private final char[] alphabet;
    private final int alphabetLength;
    private int[] indices;
    private char[] combination;

    public BruteforceAlgorithmImpl(final String alphabet) {
        this.alphabet = alphabet.toCharArray();
        this.alphabetLength = alphabet.length();
        indices = new int[1];
        combination = new char[1];
    }

    private byte[] computeNextCombination() {
        combination[0] = alphabet[indices[0]];
        final String nextCombination = String.valueOf(combination);
        if (indices[0] < alphabetLength - 1) {
            indices[0]++;
        } else {
            for (int i = 0; i < indices.length; i++) {
                if (indices[i] < alphabetLength - 1) {
                    indices[i]++;
                    combination[i] = alphabet[indices[i]];
                    break;
                } else {
                    indices[i] = 0;
                    combination[i] = alphabet[indices[i]];

                    if (i == indices.length - 1) {
                        indices = Arrays.copyOf(indices, indices.length + 1);
                        combination = Arrays.copyOf(
                                combination, combination.length + 1);
                        combination[combination.length - 1]
                                = alphabet[indices[indices.length - 1]];
                        break;
                    }
                }
            }
        }
        return nextCombination.getBytes();
    }

    @Override
    public byte[] bruteforce(byte[] secret,
            Function<byte[], byte[]> checkCondition) {
        boolean foundSecret = false;
        byte[] triedSecret = null;
        while (!foundSecret) {
            triedSecret = computeNextCombination();
            foundSecret = Arrays.equals(secret,
                    checkCondition.apply(triedSecret));
        }
        return triedSecret;
    }

}
