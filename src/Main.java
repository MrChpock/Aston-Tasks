public class Main {

    static void main() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);
        map.put("aa", 2);
        map.put("Ab", 3);

        System.out.println(map.get("a"));
        System.out.println(map.get("aa"));
        System.out.println(map.get("Ab"));

        map.remove("aa");
        System.out.println(map.get("aa"));
        System.out.println(map.get("Ab"));
    }
}
