import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;

public class TreeMath implements CaracteresEspeciales{

	Node raiz = new Node();
	Node aux = raiz;
	private String expresion;
	//ArrayList<String> RevPollNot = new ArrayList<String>();
	Stack<String> pila = new Stack<String>();
	boolean signo = false;

	public TreeMath(String expresion){
		//encolar(expresion);
		this.expresion = expresion;
		createTree();
		System.out.println(raiz);
		posfija();
		System.out.println("\n" + pila);
	}

	public void posfija(){
		posfija(raiz);
	}

	public void push(String s){
		if(s.length() == 1){
			if(!CaracteresEspeciales.caracteres.contains(s.charAt(0)))
				pila.add(s);
			else{
				System.out.println(pila);
				pila.add(resolver(s));
			}
		}
		else
			pila.add(s);
	}

	private String resolver(String operador){
		switch (operador) {
			case "+":
				return "" + (Double.parseDouble(pila.pop()) + Double.parseDouble(pila.pop()));
			case "-":
				return "" + (-Double.parseDouble(pila.pop()) + Double.parseDouble(pila.pop()));
			case "*":
				return "" + (Double.parseDouble(pila.pop()) * Double.parseDouble(pila.pop()));
			case "/":
				return "" + (1/Double.parseDouble(pila.pop()) * Double.parseDouble(pila.pop()));
		}
		return null;
	}

	private void posfija(Node node){
		if(node.hIzq != null)
			posfija(node.hIzq);
		if(node.hDer != null)
			posfija(node.hDer);
			push(node.value);
	}

	private void createTree(){
		String s = desencolar();
		if(s != null){
			if(s.equals("(")){
				aux.addhIzq();
				aux.hIzq.padre = aux;
				aux = aux.hIzq;
				createTree();
			}
			else if(s.equals(")")){
				aux = aux.padre;
				createTree();
			}
			else{
				aux.value = s;
				if(s.length() == 1){
					if(CaracteresEspeciales.caracteres.contains(s.charAt(0))){
						aux.addhDer();
						aux.hDer.padre = aux;
						aux = aux.hDer;
						createTree();
					}
					else{
						aux = aux.padre;
						createTree();
					}
				}
				else{
					aux = aux.padre;
					createTree();
				}
			}
		}	
	}

	private String desencolar(){
		if(!expresion.isEmpty()){
			String s = Character.toString(expresion.charAt(0));
			expresion = expresion.substring(1,expresion.length());
			if(!signo){
				if(CaracteresEspeciales.caracteres.contains(s.charAt(0)) || (s.equals("(")) || (s.equals(")"))){
					if(!expresion.isEmpty() && (expresion.charAt(0) == '+' || expresion.charAt(0) == '-')){
						signo = true;
					}
					return s;
				}
				while(!CaracteresEspeciales.caracteres.contains(expresion.charAt(0)) && (expresion.charAt(0) != '(') && (expresion.charAt(0) != ')')){
					s = s + expresion.charAt(0);
					expresion = expresion.substring(1,expresion.length());
				}
				return s;
			}
			else{
				signo = false;
				while(!CaracteresEspeciales.caracteres.contains(expresion.charAt(0)) && (expresion.charAt(0) != '(') && (expresion.charAt(0) != ')')){
					s = s + expresion.charAt(0);
					expresion = expresion.substring(1,expresion.length());
				}
				return s;
			}
		}
		else
			return null;
	}	
}