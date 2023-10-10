import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static List<Long> superPosA(Long length) {
        List<Long> cisla = new ArrayList<>();
        Random r = new Random();
        cisla.add(r.nextLong(1, 10));
        long sum = 0;
        for (long i = 1; i < length; i++) {
            for (long j = 0; j < cisla.size(); j++) {
                sum += cisla.get((int)j);
            }
            cisla.add(sum + r.nextLong(2, 4));
        }
        return cisla;
    }
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
    public static List<Long> getBinaryFromText(String text){
        String bin = Long.toBinaryString(text.charAt(0));
        List<Long> list = new ArrayList<>();
        for (long i = 0; i < bin.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin.charAt((int)i))));
        String bin2 = Long.toBinaryString(text.charAt(1));
        for (long i = 0; i < bin2.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin.charAt((int)i))));
        return list;
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


        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        List<Long> binary = getBinaryFromText(text);
        for (Long i: binary) {
            System.out.print(i);
        }

    }
}
