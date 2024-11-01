package containers;

import java.util.ArrayList;

public class StackRepository<T> {
	private ArrayList<T> stackList;

	public StackRepository(ArrayList<T> stack) {
		this.stackList = stack;
	}

	// push: coloca uma informação na stack
	public void push(T value) {
		stackList.add(value);
	}

	// pop: retira uma informação da stack
	public T pop() {
		if (!stackList.isEmpty()) {
			return stackList.remove(stackList.size() - 1);
		} else {
			return null;
		}
	}

	// size: retorna o tamanho da stack
	public int size() {
		return stackList.size();
	}

	// stackpop: retorna o elemento superior da stack sem removê-lo
	public T stackpop() {
		if (!stackList.isEmpty()) {
			return stackList.get(stackList.size() - 1);
		} else {
			return null;
		}
	}

	// empty: verifica se a stack está vazia
	public boolean empty() {
		return stackList.isEmpty();
	}

	public T get(int id) {
		return stackList.get(id);
	}

}
