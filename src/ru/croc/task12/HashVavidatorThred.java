package ru.croc.task12;

public class HashVavidatorThred implements Runnable {

    private final long LEAST_ITERATOR;
    private final long LARGEST_ITERATOR;
    private final String EXPECTED_HASH;
    private final PassWordGenerator PASSWORD_GEN;

    public HashVavidatorThred(long leastIterator, long largestIterator, String expectedHash, PassWordGenerator passWordGenerator) {
        LEAST_ITERATOR = leastIterator;
        LARGEST_ITERATOR = largestIterator;
        EXPECTED_HASH = expectedHash;
        PASSWORD_GEN = passWordGenerator;
    }

    @Override
    public void run() {
        String password;
        String hash;
        for (long i = LEAST_ITERATOR; i < LARGEST_ITERATOR; i++) {
            password = PASSWORD_GEN.generatePassword(i);
            hash = Hasher.hashPassword(password);
            if (hash.equals(EXPECTED_HASH)) {
                System.out.println(password);
            }
        }
    }
}
