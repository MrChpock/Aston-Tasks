public class MyHashMap<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] table;
    private int size;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Node[INITIAL_CAPACITY];
    }

    public void put(K key, V value) {
        if(key == null) {
            throw new RuntimeException();
        }

        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }

            node = node.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }

            node = node.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = Math.abs(key.hashCode() % table.length);

        Node<K, V> node = table[index];
        Node<K, V> previous = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (previous == null) {
                    table[index] = node.next;
                } else {
                    previous.next = node.next;
                }

                size--;
                return;
            }

            previous = node;
            node = node.next;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] newTable = new Node[table.length * 2];

        for (Node<K, V> kvNode : table) {
            Node<K, V> node = kvNode;

            while (node != null) {
                Node<K, V> next = node.next;

                int index = Math.abs(node.key.hashCode() % table.length);
                node.next = newTable[index];
                newTable[index] = node;

                node = next;
            }
        }

        table = newTable;
    }
}
