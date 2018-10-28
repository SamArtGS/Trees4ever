/**
 *
 * @author edaII05alu33
 */
public class Nodo {
    int valor;
	Nodo izq=null;
	Nodo der=null;
        Nodo padre=null;
        int altura;
	int indice;
        
	public Nodo(){
		izq=der=padre=null;
	}

	public Nodo(int data){
		this(data,null,null);
	}

        
	public Nodo(int data, Nodo lt, Nodo rt){
		valor=data;
		izq=lt;
		der=rt;

	}
           
	public void setIzq(Nodo izq){
		this.izq=izq;
	}
	public void setDer(Nodo der){
		this.der=der;
	}
        
        public void setPadre(Nodo padre){
            this.padre=padre;
        }
}
