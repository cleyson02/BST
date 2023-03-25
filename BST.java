import lista.EstruturaDeDados;

public class BST implements EstruturaDeDados {

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insertNode(root, key);
        }
    }

    private void insertNode(Node n, int key) {
        if (key >= n.getValue()) {
            // inserir na direita
            if (n.getRight() == null) {
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        } else {
            // inserir na esquerda
            if (n.getLeft() == null) {
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int chave) {

    }

    private void deleteNode(Node n, int key) {
        if (key >= n.getValue()) {
            Node r = n.getRight();
            if (r.getValue() == key) {
                // verificar se r é folha
                if (r.getRight() == null && r.getLeft() == null) {
                    // Caso 1
                    n.setRight(null);
                } else if (r.getRight() == null) {
                    // Caso 2
                    n.setRight(r.getLeft());
                } else if (r.getLeft() == null) {
                    // Caso 2
                    n.setRight(r.getRight());
                } else {
                    // Caso 3
                }

            }

        }
    }

    @Override
    public boolean search(int key) {
        if (root == null) {
            return false;
        }
        return searchNode(root, key);
    }

    private boolean searchNode(Node n, int key) {
        if (n.getValue() == key) {
            return true;
        } else if (key > n.getValue()) {
            if (n.getRight() == null) {
                return false;
            } else {
                return searchNode(n.getRight(), key);
            }
        } else {
            if (n.getLeft() == null) {
                return false;
            } else {
                return searchNode(n.getLeft(), key);
            }
        }
    }

    private Node searchFather(Node pai, int key) {
        boolean verify = search(key); // Para eliminar casos onde quer saber o pai de um número que não está na árvore
        if (verify == false) {
            return null;
        }
        if (pai == root) { // Para o caso onde o pai é a raiz (optei por retronar ele mesmo).
            return pai;
        }
        if (pai.getLeft() != null) {

            if (pai.getLeft().getValue() == key) {
                return pai;
            }
        }
        if (pai.getRight() != null) {
            if (pai.getRight().getValue() == key) {
                return pai;
            }
        }
        if (key > pai.getValue()) {
            return searchFather(pai.getRight(), key);

        }

        return searchFather(pai.getLeft(), key);

    }

    private Node findNode(Node atual, int key) {
        if (atual.getValue() == key) {
            return atual;
        } else if (key > atual.getValue()) {
            if (atual.getRight() == null) {
                return null;
            } else {
                return findNode(atual.getRight(), key);
            }
        } else {
            if (atual.getLeft() == null) {
                return null;
            } else {
                return findNode(atual.getLeft(), key);
            }
        }
    }

    @Override
    public int minimum() {
        return minimumNode(root).getValue();
    }

    private Node minimumNode(Node no) {
        if (no.getLeft() == null) {
            return no;
        } else {
            return minimumNode(no.getLeft());
        }
    }

    @Override
    public int maximum() {
        return maximumNode(root).getValue();
    }

    private Node maximumNode(Node no) {
        if (no.getRight() == null) {
            return no;
        } else {
            return maximumNode(no.getRight());
        }
    }

    @Override
    public int sucessor(int chave) {
        return 0;
    }

    @Override
    public int prodessor(int chave) {
        Node node = findNode(root, chave);
        if (node == null || node == minimumNode(root)) {
            return -1;
        }
        if (node.getLeft() != null) {
            return maximumNode(node.getLeft().getValue());
        }
        Node father = searchFather(root, chave);
        while (father != null && node == father.getLeft()) {
            node = father;
            father = searchFather(root, note.getValue());
        }
        if (father == null) {
            return -1;
        } else {
            return father.getNodeValue();
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        System.out.println(tree.search(7));
        // tree.insert(4);
        // tree.insert(2);
        // tree.insert(5);
        // tree.insert(6);
        // System.out.println(tree.search(5));
        // System.out.println(tree.search(7));
    }
}
