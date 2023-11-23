package Logic;

import java.security.SecureRandom;

public class RandomCodeGenerator {
    //simvoli koito shte se izpolzvat za generiraneto na koda
    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    //dyljina na koda
    private static final int CODE_LENGTH = 10;

    public static String generateRandomCode(){

        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for(int i = 0 ; i < CODE_LENGTH;i++){
            int index = random.nextInt(SYMBOLS.length());
            code.append(SYMBOLS.charAt(index));
        }
        return code.toString();
    }
}
