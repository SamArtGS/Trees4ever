public class Node {
	
	String value;
	Node padre ;
	Node hDer;
	Node hIzq;

	public Node (){

	}

	public void addNodo(String value){
		this.value = value;
	}


	public void addhDer(){
		this.hDer = new Node();
	}

	public void addhIzq(){
		this.hIzq = new Node();
	}

	@Override
	public String toString(){
		return value + "\t" + (hIzq == null ? "" : " hIzq " + hIzq) + "\t" + (hDer == null ? "" :  " hDer " + hDer) + "\n";
	}

}