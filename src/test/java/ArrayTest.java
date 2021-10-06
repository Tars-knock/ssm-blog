import java.util.Arrays;
import java.util.*;

public class ArrayTest {
    public static String licenseKeyFormatting(String s, int k) {
        int num = 0;
        s = s.toUpperCase();
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '-') continue;
            list.add(c);
        }
        System.out.println(list.toArray(new Character[1]).toString());


        // List<Character> list = new ArrayList<Character>(Arrays.asList(ArrayUtils.toObject(s.toCharArray())));

        for(int i = 0;i<list.size();i++){
            if((i - num)%k==0 && i!=0){
                list.add(i, '-');
                num++;
                i++;
            }

        }

        StringBuffer sb = new StringBuffer();
        for(Character c : list.toArray(new Character[1])){
            sb.append(c.charValue());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
    }
}
