package ru.croc.task12;

public class PassWordGenerator {

    public final String ALLOWED_CHARACTERS;
    public final int PASSWORD_LENGHT;

    public PassWordGenerator(String allowedCharacters, int passwordLenght) {
        ALLOWED_CHARACTERS = allowedCharacters;
        PASSWORD_LENGHT = passwordLenght;
    }

    public long getNumberOfVariants() {
        return (long) Math.pow(ALLOWED_CHARACTERS.length(),PASSWORD_LENGHT);
    }
    /**
     * this algorithm is based on decimal number to any base number converter
     *
     * @param index - decimal number that would be converted to password number
     * @return - number of a base of ALLOWED_CHARACTERS.length() and with leading zeros till it's length != PASSWORD_LENGHT
     */
    public String generatePassword(long index) {
        String result = "";
        long base = ALLOWED_CHARACTERS.length();
        while (result.length() != PASSWORD_LENGHT) {
            int remainder = (int) (index % base);
            result = ALLOWED_CHARACTERS.charAt(remainder) + result;
            index = index / base;
        }
        return result;
    }

}
