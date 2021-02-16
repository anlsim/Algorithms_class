package BigInteger;

public class Main {

    public static void main(String[] args) {
        BigInteger b0 = new BigInteger("-1");
        System.out.println("b0=" + b0);
        BigInteger b1 = new BigInteger(441);
        System.out.println("b1=" + b1);
        BigInteger b2;
        b2 = b0.subtract(b1);
        System.out.println("Subtracting: " + b0+ " - " + b1+ "= " + b2);
        BigInteger b3 = b0.add(b1);
        System.out.println("Adding: " + b0+ " + " + b1+ "= " + b3);



    }
}
