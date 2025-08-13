package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private int size;

        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls containsKey() with a null key");
        }
        return getNode(key, root) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        BSTNode node = getNode(key, root);
        return (node == null) ? null : node.value;
    }

    // 直接返回键所对应的节点而不是值，就能将 “值为 null 的节点” 与 “未找到时返回的 null 值” 区分开了
    private BSTNode getNode(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return getNode(key, node.left);
        } else {
            return getNode(key, node.right);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        return (node == null) ? 0 : node.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            return new BSTNode(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(key, value, node.left);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        BSTNode node = getNode(key, root);
        if (node == null) {
            return null;    // 不存在直接返回 null
        }
        V value = node.value;
        root = remove(key, root);
        return value;
    }

    private BSTNode remove(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(key, node.left);
        } else if (cmp > 0) {
            node.right = remove(key, node.right);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            BSTNode t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private BSTNode min(BSTNode node) {
        if (node == null) {
            return null;
        }
        return (node.left == null) ? node : min(node.left);
    }

    private BSTNode deleteMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        BSTNode node = getNode(key, root);
        if (node != null && (value == null ? node.value == null : value.equals(node.value))) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> keys = new TreeSet<>();
        inOrder(root, keys);
        return keys;
    }

    private void inOrder(BSTNode node, TreeSet<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void printInOrder() {
        StringBuilder s = new StringBuilder();
        printInOrder(root, s);
        System.out.println(s);
    }

    // 利用一个 helper 就不需要 for 循环进行多次遍历，只需要利用一次中序遍历就可以记录整棵树的节点了
    private void printInOrder(BSTNode node, StringBuilder s) {
        if (node == null) {
            return;
        }
        printInOrder(node.left, s);
        s.append(node.key);
        s.append(" ");
        s.append(node.value == null ? "null" : node.value.toString());
        s.append("\n");
        printInOrder(node.right, s);
    }
}
