public class TwoReceipts {
    public static void main(String[] args) {
    int kimbap = 4500;
    int water = 1000;
    int count = 2;
    int mix = kimbap + water * count;
    int pack = (kimbap + water) * count;
    int sum = kimbap + water;
        System.out.println("mix="+mix);
        System.out.println("pack="+pack);
        System.out.println("wrong="+kimbap+water);
        System.out.println("sum="+sum );

    }
}
