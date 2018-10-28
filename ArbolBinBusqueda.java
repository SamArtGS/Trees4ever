
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author HP14
 */
public class ArbolBinBusqueda {
    
    Nodo root;
    int altura;
    
    public ArbolBinBusqueda(){
        root=null;
    }
    public ArbolBinBusqueda(int val){
        root=new Nodo(val);
    }
    public ArbolBinBusqueda(Nodo root){
        this.root=root;

    } 
       
    protected void visit(Nodo n){
        System.out.println(n.valor+" ");   
    }
    
    public void breadthFirst(){
        Nodo r=root;
        Queue<Nodo> queue = new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                visit(r);
                if(r.izq!=null) 
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            
            }
        
        }
    
    }
    
    
    public boolean busqueda(int valor){
     return(busqueda1(valor,this.root));
    }    
    public boolean busqueda1(int valor, Nodo raiz){         
        if(raiz==null){            
            return false;            
        }else if(raiz.valor==valor){           
            return true;
        }else if(raiz.valor>valor){           
            return busqueda1(valor, raiz.izq);           
        }else if(raiz.valor<valor){         
            return busqueda1(valor,raiz.der);
        }   
        return false;
    }
    
    
    
     public Nodo busqueda2(int valor){
     return(busqueda2_2(valor,this.root));
    }   
    
    public Nodo busqueda2_2(int valor, Nodo raiz){
        
        if(raiz==null){
            
            return null;            
        }else if(raiz.valor==valor){
            
            return raiz;
        }else if(raiz.valor>valor){
            
            return busqueda2_2(valor, raiz.izq);
            
        }else if(raiz.valor<valor){
            
            return busqueda2_2(valor,raiz.der);
        }   
        return null;
    }
    public void add(Nodo n){
        Nodo p=this.root;
        if(!busqueda(n.valor)){           
        do{           
        if(n.valor<p.valor ){
            if( p.izq==null){
                p.izq=n;
                n.padre=p; 
            }else{
                p=p.izq;
            }
        }else if(n.valor>p.valor){
            if(p.der==null){
            p.der=n;
            n.padre=p;
            }else{
                p=p.der;
            }        
        }
        }while(n.padre==null);            
        }
    }
    
 
    public void preorden(){
        System.out.println("Notación prefija ");
        preorden1(this.root);
    }
    
    public void preorden1(Nodo raiz){
        Nodo r=raiz;
        Queue<Nodo> queue = new LinkedList();  
        
        if(r!=null){
            queue.add(r);            
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                visit(r);
                if(r.izq!=null){
                    
                    preorden1(r.izq);
                }
                if(r.der!=null){
                   
                    preorden1(r.der);
                }
            
            }
        }
        
        
        
    }
    
    public void inorden(){
    System.out.println("Notación sufija");
    inorden1(this.root);
    }
   
    public void inorden1(Nodo raiz){       
        Nodo r=raiz;          
        Queue<Nodo> queue = new LinkedList();    
        if(r!=null){
            queue.add(r);            
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                
                if(r.izq!=null){                   
                    inorden1(r.izq);
                }               
                visit(r);                
                if(r.der!=null){   
                    inorden1(r.der);
                }
            
            }
        }
    }
   
    public void postorden(){
    System.out.println("Notación posfija");
    postorden1(this.root);
    }
     
     public void postorden1(Nodo raiz){       
        Nodo r=raiz;          
        Queue<Nodo> queue = new LinkedList();    
        if(r!=null){
            queue.add(r);            
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                
                if(r.izq!=null){                   
                    postorden1(r.izq);
                }               
                               
                if(r.der!=null){   
                    postorden1(r.der);
                }
                visit(r); 
            
            }
        }
    }
        
    public int numNodos(){
        Nodo r=root;
        Queue<Nodo> queue = new LinkedList();
        int n=0;
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                
                if(r.izq!=null) 
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
                n++;
            
            }
        return n;    
        }
        return 0;
    }    
    

public int altura() {       
        altura1(this.root, 0);
        return altura;
    }

public void altura1(Nodo raiz, int nivel) {
        if (raiz != null) {            
            altura1(raiz.izq, nivel + 1);
            
            if (nivel > altura) {
                altura = nivel;
            }
            altura1(raiz.der, nivel + 1);
        }
    }

public int alturaSub(Nodo raiz) {
        int al=raiz.altura;
        
        al=altura2(raiz, 0, al);
        
        return al;
    }

public int altura2(Nodo raiz, int nivel,int al) {
        if (raiz != null) {       
            al=altura2(raiz.izq, nivel + 1, al);          
            if (nivel > al) {
               al = nivel;
            }
            al=altura2(raiz.der, nivel + 1,al);           
        }
        return al;
    }



public boolean esBalanceado(){
    int altIz;
    int altDer;
    int dif;
    
    altIz=alturaSub(root.izq);
    
    altDer=alturaSub(root.der);
   
  
    dif = Math.abs(altIz-altDer);
    return dif<=1;
    
    
}

public Nodo minimo(Nodo raiz){
    Nodo actual=raiz;
    while(actual.izq!=null){
        actual=actual.izq;
    }
    return actual;
}

public Nodo sucesor(Nodo raiz){
    Nodo suc=null;
    
    if(raiz.der!=null){
        suc=minimo(raiz.der);
    }else{
        if(raiz.padre!=null){
            if(raiz==raiz.padre.izq){
            suc=raiz.padre;
            }else{
                raiz.padre.der=null;
                suc=sucesor(raiz.padre);
                raiz.padre.der=raiz;
            }
        }
    
    }
    return suc;
     
}
    
public void borrar(Nodo raiz){
    if(raiz.izq==null && raiz.der==null){
        if( raiz==raiz.padre.izq){
            raiz.padre.izq=null;
        }else{
            raiz.padre.der=null;
        }
    }else if(raiz.izq!=null || raiz.der!=null){
        if(raiz.izq!=null){
            if(raiz==raiz.padre.izq){
                raiz.padre.izq=raiz.izq;
            }else{
                raiz.padre.der=raiz.izq;
                raiz.izq.padre=raiz.padre;
            }
        }else{
            if(raiz.izq!=null){
                raiz.padre.izq=raiz.der;
                
            }else{
                raiz.padre.der=raiz.der;
                raiz.der.padre=raiz.padre;
            }
        }
        
    }
}

 
public void eliminar1(Nodo n){
        if(n.padre!=null){
        Nodo iz=n.padre.izq;
        Nodo der=n.padre.der;
        if(iz==n){
            
            n.padre.izq=null;
            System.out.println("Eliminado");
        }
        if(der==n){
            n.padre.der=null;
            System.out.println("Eliminado");
        }   
        }
        //Eliminar raiz
        if(n==this.root){
            n=null;
        }
    }

public void eliminar2(Nodo n){
         if(n.padre!=null){
            Nodo iz=n.padre.izq;
            Nodo der=n.padre.der;

            Nodo hijo;
            //Encontrar al nodo hijo
            if(n.izq!=null){
                hijo=n.izq;
            }else{
                hijo=n.der;
            }
            //Eliminar elemento
            if(iz==n){               
                n.padre.izq=hijo;
                n.izq=n.der=n.padre=null;
                n=null;
                System.out.println("Eliminado");
            }
            if(der==n){
                n.padre.der=hijo;
                n.izq=n.der=n.padre=null;
                n=null;
                System.out.println("Eliminado");
            }
    
        }
         
         
         
         if(n==this.root){
              Nodo hijo;
            //Encontrar al nodo hijo
            if(n.izq!=null){
                hijo=n.izq;
            }else{
                hijo=n.der;
            }
            
            this.root=hijo;
            n=null;
            System.out.println("Eliminado");
        }
        
        
        
        
    }


public void eliminar3(Nodo n){
    Nodo suc=sucesor(n);
    borrar(suc);
    n.valor=suc.valor;
    
}

public void eliminar(int valor){
        Nodo n;
        n=busqueda2(valor);
        
        if(n!=null){
            //3 casos
            //Si no tiene hijos
            if(n.izq==null&& n.der==null){
                eliminar1(n);
            }else if (n.izq!=null&& n.der==null){   //si tiene un hijo
                eliminar2(n);
            }else if (n.izq==null&& n.der!=null){
                eliminar2(n);
            }else if(n.izq!=null&& n.der!=null){ //si tiene dos hijos
                eliminar3(n);
            
            }
            
            
            
            
      
        }
   
    }

}    
        
        
        
        
    
    
    

