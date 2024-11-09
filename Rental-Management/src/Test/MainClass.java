package Test;

import java.util.Stack;

public class MainClass {
    public static void main(String[] args) throws Exception{
        TestePilha testePilha = new TestePilha();
        Stack<PilhaTeste> pilhaTeste = new Stack<>();

        testePilha.push(5);
        testePilha.push(4);
        testePilha.push(3);
        testePilha.push(2);
        testePilha.push(1);
        
        pilhaTeste.push(new PilhaTeste("1984", "George Orwell"));
        pilhaTeste.push(new PilhaTeste("O Senhor dos Anéis", "J.R.R. Tolkien"));
        pilhaTeste.push(new PilhaTeste("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        /*
        while (!testePilha.isEmpty()) {
            int elementos = testePilha.pop();
            System.out.println("Desemplihei: " + elementos);
            System.out.println("Topo: " + testePilha.topo());
        }
        int e = testePilha.pop();
        */
        // Acessando o livro no topo da pilha
        System.out.println("Livro no topo: " + pilhaTeste.peek());

        // Removendo o livro do topo da pilha
        System.out.println("Livro removido: " + pilhaTeste.pop());
        System.out.println("Livro removido: " + pilhaTeste.pop());
        System.out.println("Livro removido: " + pilhaTeste.pop());

        // Verificando se a pilha está vazia
        System.out.println("A pilha está vazia? " + pilhaTeste.isEmpty());

        // Iterando sobre a pilha de livros
        System.out.println("Livros na pilha:");
        for (PilhaTeste livro : pilhaTeste) {
            System.out.println(livro);
        }
    }
    
}

/*
 * Claro! Vamos criar um exemplo de uso de pilhas com objetos personalizados em Java. Suponha que temos uma classe Livro e queremos usar uma pilha para gerenciar uma coleção de livros.

### *Classe Livro*
java
public class Livro {
    private String titulo;
    private String autor;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}


### *Uso da Pilha com Objetos Livro*
java
import java.util.Stack;

public class ExemploPilhaComObjetos {
    public static void main(String[] args) {
        // Criando uma pilha de livros
        Stack<Livro> pilhaDeLivros = new Stack<>();

        // Adicionando livros à pilha
        pilhaDeLivros.push(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
        pilhaDeLivros.push(new Livro("1984", "George Orwell"));
        pilhaDeLivros.push(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));

        // Acessando o livro no topo da pilha
        System.out.println("Livro no topo: " + pilhaDeLivros.peek());

        // Removendo o livro do topo da pilha
        System.out.println("Livro removido: " + pilhaDeLivros.pop());

        // Verificando se a pilha está vazia
        System.out.println("A pilha está vazia? " + pilhaDeLivros.isEmpty());

        // Iterando sobre a pilha de livros
        System.out.println("Livros na pilha:");
        for (Livro livro : pilhaDeLivros) {
            System.out.println(livro);
        }
    }
}


### *Explicação do Exemplo*
- **Classe Livro**:
  - *Atributos*: titulo e autor.
  - *Construtor*: Inicializa os atributos.
  - *Métodos*: getTitulo(), getAutor(), e toString() para representar o objeto como uma string.

- *Uso da Pilha*:
  - *Criação da Pilha*: Stack<Livro> pilhaDeLivros = new Stack<>();
  - *Adição de Livros*: pilhaDeLivros.push(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
  - *Acesso ao Topo*: pilhaDeLivros.peek();
  - *Remoção do Topo*: pilhaDeLivros.pop();
  - *Verificação de Vazio*: pilhaDeLivros.isEmpty();
  - *Iteração*: Usando um loop for-each.
 */
