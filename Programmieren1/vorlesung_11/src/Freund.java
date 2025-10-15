public class Freund {
    public String name;

    public Freund(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println("Ich bin " + name);
    }

    public static void main(String[] args) {
        Freund besterFreund;
        Freund[] freunde = new Freund[4];
        freunde[0] = new Freund("Siggi");
        freunde[1] = new Freund("Kalin");
        freunde[2] = new Freund("Peter");
        freunde[3] = null;

        try {
            //besterFreund = freunde[3];
            besterFreund = freunde[4];
            besterFreund.sayName();
        } catch (NullPointerException e) {
            System.out.println("Freund existiert nicht! (null)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Freund nicht gefunden!");
        }

    }
}
