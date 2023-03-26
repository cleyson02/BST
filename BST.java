import javax.swing.Painter;

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
        }

        else {
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

        deleteNode(root, chave);
        return;
    }

    private Node deleteNode(Node atual, int key) {
        Node direita = atual.getRight();
        Node esquerda = atual.getLeft();
        int Valor = atual.getValue();

        if (key < Valor) {
            atual.setLeft(deleteNode(esquerda, key));
        } else if (key > Valor) {
            atual.setRight(deleteNode(direita, key));
        } else {
            // Nó com apenas um filho ou nenhum
            if (esquerda == null) {
                return direita;
            } else if (direita == null) {
                return esquerda;
            }

            // Nó com dois filhos: pega o menor nó da subárvore da direita
            atual.setValue(minimumNode(direita).getValue());
            atual.setRight(deleteNode(direita, atual.getValue()));
        }
        return atual;
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
        boolean verify = search(key); // Para eliminar casos onde quer saber o pai de um número que não está na arvoré
        if (verify == false) {
            return null;
        }
        if (key == root.getValue()) { // Para o caso onde o pai é a raiz (Optei por retornar ele mesmo).
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
        Node atual = findNode(root, chave);

        if (atual == null || chave == maximumNode(root).getValue()) {
            System.out.println("Não há Sucessor");
            return -1;
        }
        if (atual.getRight() != null) {
            return minimumNode(atual.getRight()).getValue();
        }

        Node father = searchFather(root, chave);

        while (father != null && atual == father.getRight()) {
            atual = father;
            father = searchFather(root, atual.getValue());
        }
        if (father == null) {
            System.out.println("Não há Sucessor");
            return -1;
        } else {
            return father.getValue();
        }
    }

    @Override
    public int prodessor(int chave) {

        Node atual = findNode(root, chave);

        if (atual == null || atual == minimumNode(root)) {
            System.out.println("Não há prodessor");
            return -1;
        }
        if (atual.getLeft() != null) {
            return maximumNode(atual.getLeft()).getValue();
        }
        Node father = searchFather(root, chave);
        while (father != null && atual == father.getLeft()) {
            atual = father;
            father = searchFather(root, atual.getValue());
        }
        if (father == null) {
            return -1;
        } else {
            return father.getValue();
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(4);
        tree.insert(-1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(0);
        tree.insert(8);
        tree.insert(6);
        tree.insert(7);
        tree.insert(5);
        tree.insert(-3);
        tree.insert(-2);
        tree.insert(10);
        tree.insert(9);
        tree.insert(11);

        System.out.println(tree.search(7));
        tree.delete(7);
        System.out.println(tree.search(7));
        System.out.println(tree.maximum());
        System.out.println(tree.minimum());
        System.out.println(tree.sucessor(tree.root.getValue()));
        System.out.println(tree.prodessor(tree.root.getValue()));

        // System.out.println(tree.maximum());
        // System.out.println(tree.search(7));
        // Node temp = tree.searchFather(tree.root, );
        // System.out.println(temp.getValue());

        // System.out.println(tree.search(7));
    }
}