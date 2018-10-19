/***************************************
 *
 * This is an example of using an interface
 *
 ***************************************/

package inheritTest;

public class encryptTest {

    interface ISecure {
        String encrypt(String input);
        String decrypt(String output);
    }

    static class SecureImpl implements ISecure {
        public String encrypt(String input) {
            String tmp = "";
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'e')
                    tmp += '$';
                else
                    tmp += input.charAt(i);
            }
            return tmp;
        }

        public String decrypt(String encryptedString) {
            String tmp = "";
            for (int i = 0; i < encryptedString.length(); i++) {
                if (encryptedString.charAt(i) == '$')
                    tmp += 'e';
                else
                    tmp += encryptedString.charAt(i);

            }
            return tmp;
        }
    }

    public static void main(String[] args) {
        ISecure anEncryptTest = new SecureImpl();
        String es = anEncryptTest.encrypt("The Encrypted String");
        System.out.println(es);
        String ds = anEncryptTest.decrypt(es);
        System.out.println(ds);
    }

}