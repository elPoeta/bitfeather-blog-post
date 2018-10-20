
package elpoeta.bitfeather.util;

import java.util.regex.Pattern;

/**
 *
 * @author elpoeta
 */
public class Validar {
      public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

public static boolean isValidEmail(String emailStr) {
        //Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        //return matcher.find();
        return emailStr == null ||  VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr).find();
}
   
 public static boolean isNullOEspaciosEnBlanco(CharSequence cadena) {
    return cadena == null || cadena.codePoints().allMatch(c -> Character.isWhitespace(c));
   /*  if (cadena == null) {
        return true;
    }

    for (int i = 0; i < cadena.length(); i++) {
        if (!Character.isWhitespace(cadena.charAt(i))) {
            return false;
        }
    }

    return true;*/
}
}
