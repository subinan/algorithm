package algo.data_structure.stack;

public class StackTest {
	
	public static void main(String[] args) {
		IStack<String> stack = new Stack<>();
		
		System.out.println(stack.isEmpty() + "/" + stack.size());
		stack.push("비봉이");
		stack.push("대포");
		stack.push("금둥이");

		System.out.println("peek item: " + stack.peek());
		System.out.println(stack.isEmpty() + "/" + stack.size());
		System.out.println("pop item: " + stack.pop());
		System.out.println("pop item: " + stack.pop());
		System.out.println(stack.isEmpty() + "/" + stack.size());
		System.out.println("pop item: " + stack.pop());
		System.out.println(stack.isEmpty() + "/" + stack.size());
		stack.pop();
		
	}
}
