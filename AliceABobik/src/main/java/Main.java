import java.util.*;

public class Main {
    //private key
    public static List<Long> superPosA(Long length) {
        List<Long> cisla = new ArrayList<>();
        Random r = new Random();
        cisla.add(r.nextLong(1, 10));
        long sum = 0;
        for (long i = 1; i < length; i++) {
            for (long j = 0; j < cisla.size(); j++) {
                sum += cisla.get((int) j);
            }
            cisla.add(sum + r.nextLong(2, 4));
        }
        return cisla;
    }

    // public key
    public static List<Long> posB(List<Long> sp, Long v, Long u) {
        List<Long> cisla = new ArrayList<>();
        for (Long a : sp) {
            if ((a * v) < u) cisla.add(a * v);
            else cisla.add((a * v) % u);

        }
        return cisla;
    }

    public static Long createV(List<Long> sp, Long u) {
        Random r = new Random();
        Long rNumber = r.nextLong(1, u);
        while (isNotPrime(rNumber)) {
            rNumber = r.nextLong(1, u);
        }
        return rNumber;
    }

    public static Boolean isNotPrime(Long num) {
        if (num <= 1) return true;
        if (num == 2 || num == 3) return false;
        if (num % 2 == 0 || num % 3 == 0) return true;
        for (long i = 5; i < Math.sqrt(num); i = i + 6)
            if (num % i == 0 || num % (i + 2) == 0) return true;
        return false;
    }

    public static Long createU(List<Long> sp) {
        Random r = new Random();
        Long rNumber = r.nextLong(sp.stream().mapToLong(Long::longValue).sum(), sp.get(sp.size() - 1) * 2);
        while (isNotPrime(rNumber)) {
            rNumber = r.nextLong(sp.stream().mapToLong(Long::longValue).sum(), sp.get(sp.size() - 1) * 2);
        }
        return rNumber;
    }

    public static List<Long> getBinaryFromText(String text) {
        //text = text.isEmpty() ? text+" " : text;
        List<Long> list = new ArrayList<>();
        String bin1 = "";
        String bin2 = "";
        bin1 += Integer.toBinaryString(text.charAt(0));
        bin2 += Integer.toBinaryString(text.charAt(1));
        if (bin1.length() >= 7) bin1 = "0" + bin1;
        if (bin2.length() >= 7) bin2 = "0" + bin2;
        for (long i = 0; i < bin1.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin1.charAt((int) i))));
        for (long i = 0; i < bin2.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin2.charAt((int) i))));
        return list;
    }

    public static long decryptLetter(List<Long> spB, List<Long> bin) {
        long sum = 0; // ahoj jak se mas
        for (int i = 0; i < bin.size(); i++)
            sum += (spB.get(i) * bin.get(i));
        return sum;
    }

    public static int vMinus(long v, long u) {
        for (int i = 1; i < u - 1; i++)
            if (v * i % u == 1) return i;
        return 0;
    }

    public static String encryptLetter(long u, int vMinus, List<Long> spA, long decryptNumSum) {
        long magic = decryptNumSum * vMinus % u;
        StringBuilder binBack = new StringBuilder();
        for (int i = spA.size() - 1; i >= 0; i--) {
            Long aLong = spA.get(i);
            if (magic - aLong >= 0) {
                binBack.append(1);
                magic -= aLong;
            } else {
                binBack.append(0);
            }
        }
        char c1 = (char) (Integer.parseInt(binBack.reverse().substring(0, 8), 2));
        char c2 = (char) (Integer.parseInt(binBack.substring(8), 2));
        String a = String.valueOf(c1);
        a += String.valueOf(c2);
        return a;
    }

    public static void main(String[] args) {
        long l = 16;
        List<Long> spA = superPosA(l);
        Long u = createU(spA);
        Long v = createV(spA, u);
        List<Long> spB = posB(spA, v, u);
        System.out.println("\n--------------");
        System.out.println("Posloupnost:");
        for (Long a : spA) System.out.print(a + " ,");
        System.out.println("\n--------------");
        System.out.println("U -> " + u);
        System.out.println("V -> " + v);
        System.out.println("\n--------------");
        for (Long b : spB) System.out.print(b + " ,");
        System.out.println("\n--------------");

        int pokracovani = 1;
        while (pokracovani == 1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Alice ☺ 1)");
            System.out.println("Bobík ☻ 2)");
            int answer = Integer.parseInt(sc.nextLine());
            if (answer == 1) {
                System.out.println("Vybral jsi Alici ☺");
                System.out.println("-----------------");
                System.out.println("Public key:");
                for (Long a : spB) System.out.print(a + " ,");
                System.out.println();
                System.out.println("-----------------");
                System.out.println("Zadej císla, které si dostal od Boba, oddělené cárkami. Napr. 41641641,208157684,014530");
                if (sc.nextLine().equals("cont")) continue;
                String numbersFromBobS = sc.nextLine().trim();
                String[] arrNumBobNums = numbersFromBobS.split(",");
                List<Long> realNumsFromBob = new ArrayList<>();
                int vMinus = vMinus(v, u);
                for (String s : arrNumBobNums)
                    realNumsFromBob.add(Long.parseLong(s.trim()));
                for (Long num : realNumsFromBob) {
                    System.out.print(encryptLetter(u, vMinus, spA, num));
                }
            } else if (answer == 2) {
                System.out.println("Vybral jsi Bobíka ☻");
                System.out.println("-----------------");
                System.out.println("Zadej císla od public klíce Alice, oddělené cárkami. Napr. 394246454,216584,21,67423");
                String publicKey = sc.nextLine().trim();
                String[] publicKeyArr = publicKey.split(",");
                List<Long> realPublicKey = new ArrayList<>();
                for (String s : publicKeyArr)
                    realPublicKey.add(Long.parseLong(s.trim()));
                System.out.println("-----------------");
                System.out.println("Pecka!");
                System.out.println("-----------------");
                System.out.println("Ted napiš zprávu pro Alicku ☻♥");
                String m = sc.nextLine();
                System.out.println("-----------------");
                System.out.println("Zašifrovaná zpráva:");
                for (int i = 0; i < m.length(); i += 2) {
                    for (int j = 0; j < 2; j++) {
                        //if (i % 2 == 0)
                            System.out.print(decryptLetter(spB, getBinaryFromText(m.substring(i, i + 2))) + " ,");
                       // else System.out.print(decryptLetter(spB, getBinaryFromText(m.substring(i, i + 2))) + " ,");
                    }
                }
                System.out.println();

            }
            System.out.println();
            System.out.println("chceš znova? 1/0");
            pokracovani = Integer.parseInt(sc.nextLine());
        }


    }
}
