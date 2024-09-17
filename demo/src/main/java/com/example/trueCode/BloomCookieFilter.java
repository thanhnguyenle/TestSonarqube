package com.example.trueCode;

import java.util.BitSet;
import java.util.Random;

public class BloomCookieFilter {

    private static final int BLOOM_FILTER_SIZE = 10000; // Size of the Bloom filter
    private BitSet bloomFilter = new BitSet(BLOOM_FILTER_SIZE);
    private Random random = new Random();

    // Hash functions for the Bloom filter (example: simple modulus)
    private int[] hash(String value) {
        return new int[]{
            value.hashCode() % BLOOM_FILTER_SIZE,
            value.hashCode() * 31 % BLOOM_FILTER_SIZE
        };
    }

    // Check if the ID exists in the Bloom filter
    public boolean checkIdExists(String id) {
        int[] hashes = hash(id);
        return bloomFilter.get(hashes[0]) && bloomFilter.get(hashes[1]);
    }

    // Add a new ID to the Bloom filter
    public void addIdToBloomFilter(String id) {
        int[] hashes = hash(id);
        bloomFilter.set(hashes[0]);
        bloomFilter.set(hashes[1]);
    }

    // Generate a random ID (for simplicity, using random here)
    public String generateId() {
        return "user-" + random.nextInt(1000000);
    }

    // Handle request: Check cookie, generate and set ID
    public String handleRequest(String cookieId) {
        if (cookieId != null && checkIdExists(cookieId)) {
            // ID found in cookie and Bloom filter, return existing ID
            return cookieId;
        } else {
            // ID not found or not valid, generate a new one
            String newId;
            do {
                newId = generateId();
            } while (checkIdExists(newId)); // Ensure the new ID is unique

            // Add the new ID to the Bloom filter
            addIdToBloomFilter(newId);
            return newId; // This would be set in the user's cookie in a real scenario
        }
    }

    public static void main(String[] args) {
        BloomCookieFilter filter = new BloomCookieFilter();

        // Example request with no cookie (new user)
        String userId = filter.handleRequest(null);
        System.out.println("Generated User ID: " + userId);

        // Example request with existing cookie
        String existingUserId = filter.handleRequest(userId);
        System.out.println("Existing User ID: " + existingUserId);
    }
}
