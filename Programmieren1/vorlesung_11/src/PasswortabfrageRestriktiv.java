import java.util.Scanner;

public class PasswortabfrageRestriktiv {
    public static void main(String[] args) throws Exception {
        PasswortabfrageRestriktiv p = new PasswortabfrageRestriktiv();
        String password = p.setPassword();

        if (!password.equals("")) {
            System.out.println("New password is " + password);
        }
    }

    public String setPassword() throws Exception {
        Scanner sc = new Scanner(System.in);
        String password;

        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine();

            try {
                if (password.equals("root")) {
                    throw new IllegalPasswordException("Password too weak, please try again!");
                }
                break; // ✅ exit loop if no exception
            } catch (IllegalPasswordException e) {
                System.out.println(e.getMessage());
            }
        }

        return password;
    }

    class IllegalPasswordException extends Exception {
        public IllegalPasswordException() {}

        public IllegalPasswordException(String message) {
            super(message);
        }
    }
}
