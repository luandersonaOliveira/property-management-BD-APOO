package Test;

public class TestePilha {
    int elementos[];
    int topo;

    public TestePilha() {
        elementos = new int[10];
        topo = -1;
    }

    public void push(int e) throws Exception {
        if (isFull()) {
            throw new Exception("Stack Overflow!");
        }
        topo++;
        elementos[topo] = e;

    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack Empty!");
        }
        int e;
        e = elementos[topo];
        topo--;
        return e;
    }

    public boolean isEmpty() {
        return (topo == -1);
    }

    public boolean isFull() {
        return (topo == 9);
    }

    public int topo() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack Empty!");
        }
        return elementos[topo];
    }
}
