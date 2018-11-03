
import java.util.LinkedList;
import java.util.Queue;


public class ArbolBinBusqueda {
    //Atributos:
    Nodo root;
    int altura;
    
    //Constructores
    
    public ArbolBinBusqueda(){
        root=null;
    }
    //Crea un nodo con el valor entero recibido y lo establece como raiz
    public ArbolBinBusqueda(int val){
        root=new Nodo(val);
    }
    
    //Recibe un nodo y lo establece como raiz
    public ArbolBinBusqueda(Nodo root){
        this.root=root;
    } 
    //Imprime el atributo valor de un nodo
    protected void visit(Nodo n){
        System.out.println(n.valor+" ");   
    }
    
    //Realiza un recorrido por capas 
    public void breadthFirst(){
        //Le asigna la raíz del árbol a un nodo
        Nodo r=root;
        Queue<Nodo> queue = new LinkedList();
        //Si el nodo evaluado es diferente de null
        if(r!=null){
            //Agrega el nodo a la cola
            queue.add(r);
            //Si la cola tiene elementos
            while(!queue.isEmpty()){
                //Desencola un nodo
                r=(Nodo)queue.poll();
                //imprime el nodo
                visit(r);
                //Encola los hijos del nodo actual
                if(r.izq!=null) 
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            
            }
        
        }
    
    }
    
    //Metodo principal de busqueda
    public boolean busqueda(int valor){
        //manda a llamar al método busqueda1 enviando como argumentos el valor a buscar y el nodo raíz
     return(busqueda1(valor,this.root));
    }    
    
    public boolean busqueda1(int valor, Nodo raiz){ 
        //Si la raíz es nulla, no se encuentra el valor buscado
        if(raiz==null){            
            return false;
        // si el valor es iigual al valor del nodo actual, regresa true
        }else if(raiz.valor==valor){           
            return true;
        //Si el valor buscado es menor al valor del nodo actual, se busca en el suarbol izquierdo
        }else if(raiz.valor>valor){           
            return busqueda1(valor, raiz.izq);
        //Si el valor buscado es mayor al valor del nodo actual, se busca en el suarbol derecho
        }else if(raiz.valor<valor){         
            return busqueda1(valor,raiz.der);
        }   
        return false;
    }
    
    
    //Métodos similares a los de busqueda y busqueda1, con la diferencia de que estos devueven el nodo buscado
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
    
    
    
    
    //Agregar un nodo al arbol
    //Método brincipal, recibe un numero entero
    
    public void add(int valor){
        //Si la raíz del arbol es diferente a null
        if(this.root!=null){
            //llama al método con sobrecarga add, al cual le envia un nodo con el valor bucado
            add(new Nodo(valor));
        }else{
            //de lo contrario asigna un nuevo nodo con el valor ingresado como nueva raíz del arbol
            this.root=new Nodo(valor);
        }
    }
    
    
    public void add(Nodo n){
        //Asigana a un nodo la raiz del arbol
        Nodo p=this.root;
        //Si el nodo recibido es diferente de null
        if(n!=null){
        // si el valor a insertar no se encuentra en el arbol (para no repetir)
        if(!busqueda(n.valor)){           
        do{
        //si el valor a insertar es menor al nodo actual
        if(n.valor<p.valor ){
            //Si el nodo actual no tiene hijo izquierdo
            if( p.izq==null){
                //inserta el nodo como hijo izquierdo del nodo actual y actualiza las referencias
                p.izq=n;
                n.padre=p;
                //Despues de la inserción se realiza un control de balanceo del arbol
                balanceo1(n);
            }else{
                //De lo contrario el  nodo actual toma el lugar del hijo izquierdo
                p=p.izq;
            }
        //si el valor a insertar es mayor al nodo actual
        }else if(n.valor>p.valor){
             //Si el nodo actual no tiene hijo derecho
            if(p.der==null){
                //inserta el nodo como hijo derecho del nodo actual y actualiza las referencias
                p.der=n;
                n.padre=p;
                balanceo1(n);
            }else{
                //De lo contrario el  nodo actual toma el lugar del hijo derecho
                p=p.der;
            }        
        }
        //Se realiza este proceso hasta que se hallan cambiado el atributo padre del nodo a insertar
        }while(n.padre==null);            
        }
        }
    }
    //Recorridos por el arbol
    
//Preorden: evalúa la raíz, el subarbol izquierdo y el suarbol derecho
    public void preorden(){
        System.out.println("Notación prefija ");
        //Envia a la raíz al metodo preorden1
        preorden1(this.root);
    }
    
    public void preorden1(Nodo raiz){
        //asigna a la raíz como nodo actual
        Nodo r=raiz;
        Queue<Nodo> queue = new LinkedList();  
        //Si el nodo actual es diferente de null
        if(r!=null){
            //encola el nodo actual
            queue.add(r);
            //mientraas halla nodos en la cola           
            while(!queue.isEmpty()){
                //desencola un nodo
                r=(Nodo)queue.poll();
                //lo mrca como visitado
                visit(r);
                //si el nodo actual tiene hijo izquierdo realiza preorden sobre el hijo izquierdo 
                if(r.izq!=null){
                    
                    preorden1(r.izq);
                }
                //si el nodo actual tiene hijo derecho realiza preorden sobre el hijo derecho 
                if(r.der!=null){
                   
                    preorden1(r.der);
                }
            
            }
        }
        
        
        
    }
    
    
    //Realiza los mismos pasos que en preorden, solo cambia el orden de evaluar:
    //Preorden: evalúa el subarbol izquierdo, la raíz y el suarbol derecho
    public void inorden(){
    //Envia a la raíz al metodo inorden1
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
   
    //Realiza los mismos pasos que en preorden, solo cambia el orden de evaluar:
    //Postorden: evalúa el subarbol izquierdo, el suarbol derecho y  la raíz.
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
    
     
    //Método que cuenta el numero de nodos del árbol 
    public int numNodos(){
        Nodo r=root;
        Queue<Nodo> queue = new LinkedList();
        int n=0;
        //Realiza un recorrido por capas BFS
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                
                if(r.izq!=null) 
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
                //Cada vez que agrega un nodo a la cola el contador aumenta 1
                n++;
            
            }
        return n;    
        }
        return 0;
    }    
   
    //Método que calcula la altura del árbol
public int altura() { 
    //Llama al método altura y le envia la raíz y el nivel actual 0
        altura1(this.root, 0);
        return altura;
    }

public void altura1(Nodo raiz, int nivel) {
    //Si la raíz recibida es diferente de null
        if (raiz != null) {
            //Evalua la altura del subarbol izquierdo
            //Se llama a sí mismo aumentando el nivel
            altura1(raiz.izq, nivel + 1);
            //si el niver es mayor que la altura del arbol
            if (nivel > altura) {
                //La alturá del arbol se actualiza
                altura = nivel;
            }
            //Evalua la altura del subarbol derecho
            altura1(raiz.der, nivel + 1);
        }
    }

//Método que calcula la altura de un subarbol y devuelve su valor
//Estos métodos son parecidos a los de altura y altura1 con la diferencia de que devulven un nuero entero con 
//la altura del subarbol evaluado
public int alturaSub(Nodo raiz) {
    if(raiz!=null){
        int al=raiz.altura;        
        al=altura2(raiz, 0, al);       
        return al;
    }else{
    return -1;
    }
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

//Método que evalua el estado de balanceo del arbol, regresa un booleano

public boolean esBalanceado(){
    //crea tres variables que almacenan la altura del subarbol izquierdo, la altura del subarbol derecho y
    //la diferencia entre estos dos
    int altIz;
    int altDer;
    int dif;
    //Obtiene la altura de cada subarbol
    altIz=alturaSub(root.izq);
    
    altDer=alturaSub(root.der);
   
  //realiza una resta entre las alturas y obtiene un valor absoluto
    dif = Math.abs(altIz-altDer);
    //regresa verdadero si la diferencia es menor o igual a 1
    return dif<=1;
    
    
}

//Encuentra el menor nodo del subarbol, el nodo que se encuentra mas a la izquierda
public Nodo minimo(Nodo raiz){
    Nodo actual=raiz;
    while(actual.izq!=null){
        actual=actual.izq;
    }
    return actual;
}

//encuentra el nodo sucesor de un nodo con dos hijos
public Nodo sucesor(Nodo raiz){
    Nodo suc=null;
//Si la raiz tiene hijo derecho, el sucesor es el menor nodo del suarbol derecho    
    if(raiz.der!=null){
        suc=minimo(raiz.der);
        
    }else{
        //De lo contrario, si la raiz tiene padre
        if(raiz.padre!=null){
            //Si el nodo actual es el hijo izquierdo
            if(raiz==raiz.padre.izq){
            //El sucesor es su padre
            suc=raiz.padre;
            }else{
                //Si es el hijo derecho
                raiz.padre.der=null;
                //El sucesor será el sucesor del padre
                suc=sucesor(raiz.padre);
                raiz.padre.der=raiz;
            }
        }
    }
    return suc;
     
}

//Borra al nodo con dos hijos
public void borrar(Nodo raiz){
    //Si el sucesor no tiene hijos, lo borra
    if(raiz.izq==null && raiz.der==null){
        if( raiz==raiz.padre.izq){
            raiz.padre.izq=null;
            balanceo1(raiz.padre);
        }else{
            raiz.padre.der=null;
            balanceo1(raiz.padre);
        }
        //si el sucesor tiene algun hijo, lo borra
    }else if(raiz.izq!=null || raiz.der!=null){
        //si tien hijo izquierdo
        if(raiz.izq!=null){
            if(raiz==raiz.padre.izq){
                raiz.padre.izq=raiz.izq;
                balanceo1(raiz.padre);
        //si tien hijo derecho
            }else{
                
                raiz.padre.der=raiz.izq;
                raiz.izq.padre=raiz.padre;
                balanceo1(raiz.padre);
            }
        }else{ 
            if(raiz.izq!=null){
                raiz.padre.izq=raiz.der;
                balanceo1(raiz.padre);
            }else{
                raiz.padre.der=raiz.der;
                raiz.der.padre=raiz.padre;
                balanceo1(raiz.padre);
            }
        }
        
    }
}

//Si no tiene hijos
public void eliminar1(Nodo n){
        //Si tien padre
        if(n.padre!=null){
        Nodo iz=n.padre.izq;
        Nodo der=n.padre.der;
        //Averigua que hijo es
        //Si es el ijo izquierdo
        if(iz==n){
            //Cambia las referencias del hijo izquierdo del padre
            n.padre.izq=null;
            n.padre=null;
            System.out.println("Eliminado");
        }
        if(der==n){
            //De lo contrario cambia las referencias del hijo derecho del padre
            n.padre.der=null;
            n.padre=null;
            System.out.println("Eliminado");
        }   
        }
        //Eliminar raiz
        if(n==this.root){
            n=null;
        }
    }

//Si el nodo a eliminar tiene un hijo

public void eliminar2(Nodo n){
        //Si tiene padre
         if(n.padre!=null){
            Nodo iz=n.padre.izq;
            Nodo der=n.padre.der;

            Nodo hijo;
            //Encontrar el hijo del nodo actual y asignarlo al nodo hijo
            if(n.izq!=null){
                hijo=n.izq;
            }else{
                hijo=n.der;
            }
            //Eliminar elemento
            //Encontrar que clase de hijo es el nodo actgual
            //Si el nodo actual es el hijo izquierdo
            if(iz==n){ 
                //El nuevo hijo izquierdo del padre del nodo actual es el unico hijo del nood actual
                n.padre.izq=hijo;
                n.izq=n.der=n.padre=null;
                n=null;
               
            }
             //Si el nodo actual es el hijo derecho
            if(der==n){
                //El nuevo hijo derecho del padre del nodo actual es el unico hijo del nood actual
                n.padre.der=hijo;
                n.izq=n.der=n.padre=null;
                n=null;
                
            }
    
        }
         
         
         //Si se elimina la raiz y tiene un solo hijo
         if(n==this.root){
              Nodo hijo;
            //Encontrar al nodo hijo
            if(n.izq!=null){
                hijo=n.izq;
            }else{
                hijo=n.der;
            }
            //Cambiar la raíz por el nodo hijo
            this.root=hijo;
            n=null;
           
        }
        
        
        
        
    }

//Si el nodo a eliminar tiene dos hijos
public void eliminar3(Nodo n){
    System.out.println("Sucesor");
    //Encontrar al nodo sucesor
    Nodo suc=sucesor(n);
    //Borrar el nodo sucesor del arbol
    borrar(suc);
    //Cambia el valor del nodo a eliminar al valor del sucesor
    n.valor=suc.valor;
    
}

//Método principal de eliminar, recibe el valor del entero a eliminar
public void eliminar(int valor){
    //Crea un nodo al cual le asigna el nodo a eliminar
        Nodo n;
        n=busqueda2(valor);
        //Si el nodo a eliminar es diferente de null
        if(n!=null){
            //3 casos
            //Si no tiene hijos
            if(n.izq==null&& n.der==null){
                //Elimina el nodo y realiza el balanceo
                eliminar1(n);
                balanceo1(n);
            //si tiene un hijo
            }else if (n.izq!=null&& n.der==null){   //si tiene un hijo izquierdo
                 //Elimina el nodo y realiza el balanceo
                eliminar2(n);
                 balanceo1(n);
            }else if (n.izq==null&& n.der!=null){   //si tiene un hijo derecho
                 //Elimina el nodo y realiza el balanceo
                eliminar2(n);
                 balanceo1(n);
            }else if(n.izq!=null&& n.der!=null){ //si tiene dos hijos
                 //Elimina el nodo y realiza el balanceo
                eliminar3(n);
                 balanceo1(n);
            
            }
            
            
            
            
      
        }
   
    }
 
//Calcular factor de balanceo de cada nodo
public void calcularFactor(Nodo n){
    int altIz;
    int altDer;
    int dif;
    
   //Obtener altura del suarbol izquierdo
    altIz=alturaSub(n.izq); 
    //Obtener altura del suarbol derecho
    altDer=alturaSub(n.der);
    //Obtener deferencia entre alturas
    dif = altDer-altIz;
    //Establecer la diferencia como el factor de balanceo
    n.indice=dif;
    
}
//Establecer factor establece el factor de balanceo para todos los nodos del arbol mediantre un recorrido BFS
public void estableceFactor(){
    Nodo r=root;
        Queue<Nodo> queue = new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                calcularFactor(r);
                if(r.izq!=null) 
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            
            }
        
        }
    
}

//Realiza una rotacion simple a la izquierda
public void rotacionSimpleIzq(Nodo n){
    //Se guarda el nodo raiz
    Nodo a=new Nodo();
    a=n;
    //cambio de riz
    if(n==root){ //Si el balanceo se realiza sobre la raíz 
        //La nueva raíz es el hijo derecho de la raiz
        root=root.der;
        //El nuevo hijo derecho del nodo actual es el hijo izquierdo de la nue//Obtener altura del suarbol izquierdoa raíz
        a.der=root.izq;
        //El nuevo hijo izquierdo de la raíz es el nodo actual
        root.izq=a;
        //El nuevo padre del nodo actual es la neva raíz
        a.padre=root;
        //Si el nodo actual tiene hijo derecho se establece como su padre
        if(a.der!=null)
            a.der.padre=a;
        //Se establece como null el hijo de la raíz
        root.padre=null;
    }else{
        //Si el nodo no es raíz del arbol
        n=n.der;
        a.der=n.izq;
        n.izq=a;
        n.padre=a.padre;
        //Encuentra de que hijo se trata y cambia su posicion con el nuevo nodo raíz
        if(a==a.padre.izq){
            a.padre.izq=n;
        }else{
            a.padre.der=n;
        }
        a.padre=n;
        if(a.der!=null)
            a.der.padre=a;
        
    }
    
    
}
//Realiza las mismas operaciones que el anterior, solo cambia las variables izquierdas por derechas
public void rotacionSimpleDer(Nodo n){
    Nodo a=new Nodo();
    a=n;
    if(n==root){
        root=root.izq;
        a.izq=root.der;   
        root.der=a;
        a.padre=root;
        if(a.izq!=null)
            a.izq.padre=a;
        root.padre=null;
    }else{
        n=n.izq;
        a.izq=n.der;   
        n.der=a;
        n.padre=a.padre;
    if(a==a.padre.izq){
        a.padre.izq=n;
    }else{
        a.padre.der=n;
    }
   
    a.padre=n;
    if(a.izq!=null)
        a.izq.padre=a;
    
    }
    
    
    
}

/*Realiza una rotacion simple a la derecha sobre el hijo derecho del nodo actual y posteriormente una rotacion
simple a la izquierda sobre el nodo actual
*/
public void rotacionDobleIzq(Nodo n){
    rotacionSimpleDer(n.der);
    rotacionSimpleIzq(n);
   
}
/*Realiza una rotacion simple a la izquierda sobre el hijo izquierdo del nodo actual y posteriormente una rotacion
simple a la derecha sobre el nodo actual
*/
public void rotacionDobleDer(Nodo n){
    rotacionSimpleIzq(n.izq);
    rotacionSimpleDer(n);
}

//Realiza un balanceo dependiendo del factor de balanceo
public void balanceo2(Nodo n){
    
    System.out.println("balanceo "); 
    //Si el indice es mayor que uno y tiene hijo derecho
    if(n.indice>1){
        if(n.der!=null){
            //Si el índice del hijo derecho es mayor o igual a 0
            if(n.der.indice >=0){
                System.out.println("balanceo simple izq");
                //Realiza rotación simple a la izquierda
                rotacionSimpleIzq(n);
                //Si el índice del hijo derecho es menor a 0
            }else{
                System.out.println("balanceo doble izq");
                //Realiza rotación doble a la izquierda
                rotacionDobleIzq(n);
            }
        }
 //Si el indice es menor que menos uno y tiene hijo derecho
    }else if(n.indice<-1){
         if(n.der!=null){
             //Si el índice del hijo izquierdo es menor o igual a 0
             if(n.izq.indice <=0 ){
                 System.out.println("balanceo simple Der");
                 //Realiza rotación simple a la derecha
                rotacionSimpleDer(n);
                //Si el índice del hijo izquierdo es menor a 0
             }else{
                  System.out.println("balanceo doble der");
                  //Realiza rotación doble a la derecha
                  rotacionDobleDer(n);
             }
         }
    }
  
    
}
//Balanceo se aplica soble el nodo actual y sobre toda su ascendencia 
public void balanceo1(Nodo n){
    estableceFactor();
    Nodo r=n;
        Queue<Nodo> queue = new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                estableceFactor();
                r=(Nodo)queue.poll();
                //Si el indice del nodo evaluado es mayor a 1 o menor que -1 se realiza un balanceo
                if(r.indice<-1 || r.indice>1)
                    balanceo2(r);
                //Si el nodo actual tiene padre, se agrega en la cola
                if(r.padre!=null) 
                    queue.add(r.padre);           
            }
        
        }
    
    
}

}

        
        
    
    
    

