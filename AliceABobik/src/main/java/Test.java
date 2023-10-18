import java.util.ArrayList;
import java.util.List;

public class Test {
    public static List<Long> getBinaryFromText(String text) {
        String bin1 = "";
        String bin2 = "";
        bin1 += Integer.toBinaryString(text.charAt(0));
        bin2 += Integer.toBinaryString(text.charAt(1));

        if (bin1.length() >= 7) bin1 = "0" + bin1;
        if (bin2.length() >= 7) bin2 = "0" + bin2;
        List<Long> list = new ArrayList<>();
        for (long i = 0; i < bin1.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin1.charAt((int) i))));
        for (long i = 0; i < bin2.length(); i++)
            list.add(Long.parseLong(String.valueOf(bin2.charAt((int) i))));
        return list;
    }
    public static void main(String[] args) {
        List<Long> list =getBinaryFromText("oooo");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+"."+list.get(i));
        }

    }
}
