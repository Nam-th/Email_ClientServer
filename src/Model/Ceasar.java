/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.Map;

public class Ceasar {

     private static final String VIETNAMESE_CHARS = "abcdefghijklmnopqrstuvwxyzäöüàáảãạèéẻẽẹìíỉĩịòóỏõọùúủũụýỳỷỹỵ";
    private static final Map<Character, Integer> CHAR_TO_INDEX = new HashMap<>();
    private static final Map<Integer, Character> INDEX_TO_CHAR = new HashMap<>();
    
    static {
        for (int i = 0; i < VIETNAMESE_CHARS.length(); i++) {
            CHAR_TO_INDEX.put(VIETNAMESE_CHARS.charAt(i), i);
            INDEX_TO_CHAR.put(i, VIETNAMESE_CHARS.charAt(i));
        }
    }

    public String Encrypt(String br, int k) {
        StringBuilder result = new StringBuilder();
        for (char c : br.toCharArray()) {
            Integer index = CHAR_TO_INDEX.get(c);
            if (index != null) {
                int newIndex = (index + k + VIETNAMESE_CHARS.length()) % VIETNAMESE_CHARS.length();
                result.append(INDEX_TO_CHAR.get(newIndex));
            } else {
                result.append(c); // Giữ nguyên các ký tự không có trong bảng mã
            }
        }
        return result.toString();
    }

    public String Decrypt(String encryptedText, int k) {
        StringBuilder result = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            Integer index = CHAR_TO_INDEX.get(c);
            if (index != null) {
                int newIndex = (index - k + VIETNAMESE_CHARS.length()) % VIETNAMESE_CHARS.length();
                result.append(INDEX_TO_CHAR.get(newIndex));
            } else {
                result.append(c); // Giữ nguyên các ký tự không có trong bảng mã
            }
        }
        return result.toString();
    }
}
