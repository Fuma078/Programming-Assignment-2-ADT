import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleTextAnalyzer {
    public static void main(String[] args) {
        // Start time for measuring execution time
        long startTime = System.currentTimeMillis();
        
        // Variables to store
        int totalCharacters = 0;
        int totalPalindromes = 0;
        int totalWords = 0;
        int totalEmoticons = 0;
        int totalLines = 0;
        int longestWordLength = 0;
        int totalWordLength = 0;

        BufferedReader reader = null;
        
        try {
            // Open the file "input1.txt" for reading
            reader = new BufferedReader(new FileReader("input1.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                totalLines++; // Count lines
                totalCharacters += line.length(); // Count characters

                // Split the line into words based on spaces
                String[] words = line.split(" +");
                totalWords += words.length;

                // Loop through each word
                for (String word : words) {
                    // Remove non-letter characters and convert to lowercase
                    String cleanWord = "";
                    for (char c : word.toCharArray()) {
                        if (Character.isLetterOrDigit(c)) {
                            cleanWord += Character.toLowerCase(c);
                        }
                    }
                    
                    // Check if the word is a palindrome
                    if (cleanWord.length() > 1 && isPalindrome(cleanWord)) {
                        totalPalindromes++;
                    }

                    // Track the longest word length
                    if (word.length() > longestWordLength) {
                        longestWordLength = word.length();
                    }
                    totalWordLength += word.length();

                    // Check if the word is an emoticon
                    if (word.equals(":)") || word.equals(":D") || word.equals(":(")) {
                        totalEmoticons++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Close the file
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }

        // Simple way to slow down execution without using Thread.sleep
        for (long i = 0; i < 50_000_000; i++) {
            Math.sqrt(i); // Do some unnecessary calculations
        }

        // Calculate average word length
        double averageWordLength = totalWords == 0 ? 0 : (double) totalWordLength / totalWords;
        
        // Calculate execution time
        double executionTime = (System.currentTimeMillis() - startTime) / 1000.0;

        // Print results
        System.out.println("Program start:");
        System.out.println("Total Characters: " + totalCharacters);
        System.out.println("Total Palindromes: " + totalPalindromes);
        System.out.println("Total Words: " + totalWords);
        System.out.println("Total Emoticons: " + totalEmoticons);
        System.out.println("Total Lines: " + totalLines);
        System.out.println("Longest Word Length: " + longestWordLength);
        System.out.printf("Average Word Length: %.2f\n", averageWordLength);
        System.out.printf("Execution Time: %.1f seconds\n", executionTime);
        System.out.println("Program ended!");
    }

    // Function to check if a word is a palindrome
    private static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
