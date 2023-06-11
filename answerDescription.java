/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author User
 */
public class answerDescription {
    public static void main(String[] args) {
        int encryptedNumber = 17355;

        // Step 1: Convert the encrypted number to a binary string
        String binaryString = Integer.toBinaryString(encryptedNumber);
        System.out.println("Encrypted binary string: " + binaryString);

        // Step 2: Divide the binary string into blocks of three digits
        String[] blocks = divideIntoBlocks(binaryString);
        System.out.println("Blocks: " + Arrays.toString(blocks));

        // Step 3-6: Decrypt each block
        for (int j = 0; j <= 9; j++) {
                    int secretKey = 7;

            System.out.println("Shift the secret key by "+j+" place(s)");
            StringBuilder decryptedBinary = new StringBuilder();
            for (int i = blocks.length - 1; i >= 0; i--) {
                // Step 3a: Convert the block back to an integer
                int blockValue = Integer.parseInt(blocks[i], 2);
                // Step 3b: Subtract the value of (secret key modulo 2) from the block value
                blockValue -= secretKey % 2;

                // Step 3c: Convert the value back to binary format with three digits
                String decryptedBlock = String.format("%03d", Integer.parseInt(Integer.toBinaryString(blockValue)));

                // Step 3d: Shift the bits of secretKey to the right by a positive integer below 10
                secretKey = (secretKey >> j) % 10;

                // Append the decrypted block to the final decrypted binary string
                decryptedBinary.insert(0, decryptedBlock);
            }
            System.out.println("Decrypted binary string: " + decryptedBinary);

            // Step 7: Convert the decrypted binary string back to an integer
            int decryptedNumber = Integer.parseInt(decryptedBinary.toString(), 2);
            System.out.println("Decrypted number: " + decryptedNumber);
            System.out.println("-----------------------------------");
        }
    }

    private static String[] divideIntoBlocks(String binaryString) {
        // Pad the binary string with leading zeros to ensure the length is a multiple of three
        int paddingLength = 3 - (binaryString.length() % 3);
        if (paddingLength < 3) {
            binaryString = "0".repeat(paddingLength) + binaryString;
        }

        // Divide the binary string into blocks of three digits
        int numBlocks = binaryString.length() / 3;
        String[] blocks = new String[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            blocks[i] = binaryString.substring(i * 3, (i * 3) + 3);
        }

        return blocks;
    }
}