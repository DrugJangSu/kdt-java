import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        // map은 key와 value의 쌍으로 이루어져 있다.
        HashMap<String,String> orders = new HashMap<>();

        orders.put("mina", "americano");
        orders.put("jun", "kimbap");

        System.out.println(orders.get("mina"));
        System.out.println(orders.get("jun"));

        // c와 u의 형태를 띄고 있다.
        orders.put("mina", "kimbap");

        // update 형식을 띄고 있다.
        orders.replace("jun", "americano");

        // 조건부 수정-> mina라는 키값을 가진 친구의 현재 값이랑 비교해서, oldValue가 맞다면 newValue로 수정(블일치 시 변경X)
        orders.replace("mina", "kimbap", "americano");

        // 키는 언제나 고유해야 함.

        // 키값을 내가 검색해서 실제 map에 키가 있는지 검사 -> containsKey(키값)
        if (orders.containsKey("mina")) {
            System.out.println(orders.get("mina"));
        } else {
            System.out.println("missing");
        }

        // 키값만 순차적으로 반환.
        for (String name : orders.keySet()) {
            System.out.println(name);
            System.out.println(orders.get(name));
        }

        System.out.println("================");
        // 밸류값만 순차적으로 반환.
        for (String value : orders.values()) {
            System.out.println(value);
        }
    }
}
