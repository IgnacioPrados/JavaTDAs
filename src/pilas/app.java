package pilas;
import conjuntos.*;

public class app {

    /*--------------------------------- MAIN ---------------------------------*/
    public static void main(String[] args) throws Exception {
        PilaTI pila = new PilaTI();
        pila.InicializarPila();
        pila.Apilar(3);
        pila.Apilar(1);
        pila.Apilar(2);
        pila.Apilar(1);
        pila.Apilar(3);


        System.out.println(esCapicua(pila));

        System.out.println(pila.Tope());
        System.out.println(pila.Mostrarpila());
        pila.Desapilar();
        pila.Desapilar();
        System.out.println(pila.Tope());

        System.out.println(pila.Mostrarpila());
        System.out.println(mostrarPila(pila));

    }

    /*--------------------------------- METODOS EXTRA ---------------------------------*/

    // funcion para imprimir la pila usando PilaTDA ---------------
    public static String mostrarPila(PilaTDA pila) {
        String resultado = "[ ";
        while (!pila.PilaVacia()) { // mientras no este vacio la pila
            resultado += pila.Tope() + " ";
            pila.Desapilar();
        }
        resultado += "]";
        return resultado;
    }

    // funcion para pasar una Pila a otra Pila (dejándola en orden inverso) - TP1 Ejercicio 2a ---------------
    public static void pasarPila(PilaTDA origen, PilaTDA destino) {
        while (!origen.PilaVacia()) { // mientras no este vacia la pila
            destino.Apilar(origen.Tope()); // apilar el tope de la pila origen en la pila destino
            origen.Desapilar(); // desapilar el tope de la pila origen
        }
    }

    // funcion para copiar una Pila en otra (dejándola en el mismo orden que la original) - TP1 Ejercicio 2b ---------------
    public static void copiarPila(PilaTDA origen, PilaTDA destino) {
        PilaTDA aux = new PilaTF(); // crear una pila auxiliar
        aux.InicializarPila();

        while (!origen.PilaVacia()) { // mientras no este vacia la pila
            aux.Apilar(origen.Tope()); // apilar el tope de la pila origen en la pila auxiliar
            origen.Desapilar();
        }
        while (!aux.PilaVacia()) { // volver a apilar los elementos de la pila auxiliar en la pila destino
            destino.Apilar(aux.Tope());
            aux.Desapilar();
        }
    }

    // funcion para invertir el contenido de una Pila - TP1 Ejercicio 2c ---------------
    public static void invertirPila(PilaTDA origen) {
        PilaTDA aux = new PilaTF();
        PilaTDA aux2 = new PilaTF(); // crear dos pilas auxiliares
        aux.InicializarPila();
        aux2.InicializarPila();

        while (!origen.PilaVacia()) { // apilar el tope de la pila origen en la pila auxiliar
            aux.Apilar(origen.Tope());
            origen.Desapilar();
        }
        while (!aux.PilaVacia()) { // volver a apilar los elementos de la pila auxiliar en la axiliar 2
            aux2.Apilar(aux.Tope());
            aux.Desapilar();
        }
        while (!aux2.PilaVacia()) { // volver a apilar los elementos de la pila auxiliar en la pila origen
            origen.Apilar(aux2.Tope());
            aux2.Desapilar();
        }
    }

    // funcion para contar los elementos de una Pila - TP1 Ejercicio 2d ---------------
    public static int contarElementos(PilaTDA origen) {
        int i = 0;

        while (!origen.PilaVacia()) {  // mientras no este vacia la pila
            i++;
            origen.Desapilar(); // desapilar el tope de la pila origen y aumentar el contador
        }
        return i;
    }

    // funcion para sumar los elementos de una Pila - TP1 Ejercicio 2e ---------------
    public static int sumarElementos(PilaTDA origen) {
		int n = 0;

		while (!origen.PilaVacia()) {   // mientras no este vacia la pila
			n += origen.Tope();
			origen.Desapilar(); // desapilar el tope de la pila origen y sumar el contador con el tope de la pila
		}
		return n;
	}

    // funcion para calcular el promedio de los elementos de una Pila - TP1 Ejercicio 2f ---------------
    public static float CalcularPromedio(PilaTDA origen) {
		float suma = 0;
		float resultado;
		float cantidad = 0;

		while (!origen.PilaVacia()) {   
			suma += origen.Tope();  // sumar el tope de la pila
			origen.Desapilar();
			cantidad++; // aumentar el contador
		}
		resultado = suma / cantidad;    // calcular el promedio
		return resultado;
	}

    // funcion para determinar si una Pila es capicúa o no. Para ser capicúa debe cumplir  ---------------
	// que el primer elemento es igual al último, el segundo igual al penúltimo, etc - TP3 Ejercicio 1a
	public static boolean esCapicua(PilaTDA origen) {
		PilaTDA aux = new PilaTF();
        PilaTDA aux2 = new PilaTF();
        PilaTDA aux3 = new PilaTF();    // crear tres pilas auxiliares
        aux.InicializarPila();
        aux2.InicializarPila();
        aux3.InicializarPila();

        boolean capicua = true;

        while (!origen.PilaVacia()) { // apilar la pila origen en la pila auxiliar 1 y 2
            aux.Apilar(origen.Tope());
            aux2.Apilar(origen.Tope());
            origen.Desapilar();
        }

        while (!aux.PilaVacia()) { // apilar la pila aux en la pila origen
            origen.Apilar(aux.Tope());
            aux3.Apilar(aux.Tope());    // apilar la pila auxiliar 1 en la pila auxiliar 3
            aux.Desapilar();
        }

        while (!aux2.PilaVacia()) { 
            if (aux2.Tope() != aux3.Tope()) {   // si el tope de la pila auxiliar 2 (invertida) no es igual al tope de la pila auxiliar 3 (como en origen)
                capicua = false;
            }
            aux2.Desapilar();
            aux3.Desapilar();
        }
        return capicua;

    }

    // funcion para eliminar de una Pila P las repeticiones de elementos, dejando un representante de cada uno de ---------------
    // los elementos presentes originalmente. Se deberá respetar el orden original de los elementos, y en el caso de los 
    // repetidos se conservará el primero que haya ingresado en P - TP3 Ejercicio 1b
    public static void eliminarRepetidos(PilaTDA origen) {
        PilaTDA aux = new PilaTF();;
        aux.InicializarPila();  // crear una pila auxiliar

        int cant = 0;   // contador de elementos
        int g = 0;
        int j = 0;

        while (!origen.PilaVacia()) { // obtener la cantidad de elementos de la pila origen
            aux.Apilar(origen.Tope());
            origen.Desapilar();
            cant++;
        }

        while (cant > 0) {
            for (int i = j; i >= 0; i--) {  // recorrer la pila auxiliar
                g = aux.Tope();
                origen.Apilar(g);   // apilar la pila auxiliar en la pila origen
                aux.Desapilar();
            }

            while (!aux.PilaVacia()) {
                if (aux.Tope() != g) {  // si el tope de la pila auxiliar es diferente al tope de la pila origen
                    origen.Apilar(aux.Tope());  // apilar la pila auxiliar en la pila origen
                } else {
                    cant--; // decrementar el contador
                }
                aux.Desapilar();
            }

            while (!origen.PilaVacia()) {  // apilar la pila origen en la pila auxiliar
                aux.Apilar(origen.Tope());
                origen.Desapilar();
            }
            cant--;
            j++;
        }

        while (!aux.PilaVacia()) {  // apilar la pila auxiliar en la pila origen (para respetar el orden original)
            origen.Apilar(aux.Tope());
            aux.Desapilar();
        }
    }

    // funcion para eliminar de una Pila P las repeticiones de elementos, dejando un representante de cada uno de ---------------
    // los elementos presentes originalmente. Se deberá respetar el orden original de los elementos, y en el caso de los 
    // repetidos se conservará el primero que haya ingresado en P - TP3 Ejercicio 1b (MEJORADO)
    public static PilaTDA eliminarRepetidosMejorado(PilaTDA origen) {
        PilaTDA aux = new PilaTF();;
        aux.InicializarPila();  // crear una pila auxiliar

        ConjuntoTDA conjunto = new ConjuntoA(); // crear un conjunto para guardar los elementos de la pila origen
        conjunto.InicializarConjunto();

        while (!origen.PilaVacia()) { // pasar los elementos de la pila al conjunto y pila aux
            int x = origen.Tope();
            conjunto.Agregar(x);
            aux.Apilar(x);
            origen.Desapilar();
        }

        while (!aux.PilaVacia()) { // pasar los elementos de la pila al origen (si no esta repetido se agrega)
            int x = aux.Tope();
            if (conjunto.Pertenece(x)) {    // si el elemento esta en el conjunto
                origen.Apilar(x);
                conjunto.Sacar(x);  // lo agrego al origen y lo saco del conjunto
            } 
            aux.Desapilar();
        }
        return origen;
    }

    // Repartir una Pila P en dos mitades M1 y M2 de elementos consecutivos, respetando el orden. ---------------
    // Asumir que la Pila P contiene un número par de elementos. - TP1 Ejercicio 1c
    public static void Repartir(PilaTDA pila, PilaTDA M1, PilaTDA M2) {
		
        PilaTDA aux = new PilaTF(); // crear una pila auxiliar
        aux.InicializarPila();

        int cant = 0; // cantidad de elementos de la pila

        while (!pila.PilaVacia()) { // obtener la cantidad de elementos de la pila
            aux.Apilar(pila.Tope());
            pila.Desapilar();
            cant++;
        }

        while (!aux.PilaVacia()) {  // apilar la pila auxiliar en la pila origen para no perder el orden
            pila.Apilar(aux.Tope());
            aux.Desapilar();
        }

        for (int i = 0; i < cant/2; i++) { // repartir la pila en dos mitades (1 mitad para M1)
            M1.Apilar(pila.Tope());
            pila.Desapilar();
        }

        while (!pila.PilaVacia()) {  // repartir la pila en dos mitades (2 mitad para M2)
            M2.Apilar(pila.Tope());
            pila.Desapilar();
        }

    }

    // funcion para generar el conjunto de elementos que se repiten en una Pila - TP1 Ejercicio 1d ---------------
    public static ConjuntoTDA ConjuntoRepetidos(PilaTDA origen) {

        ConjuntoTDA aux = new ConjuntoA();  // crear 2 conjuntos auxiliares
        aux.InicializarConjunto();
        ConjuntoTDA aux2 = new ConjuntoA();
        aux2.InicializarConjunto();

        while (!origen.PilaVacia()) { // pasar a aux2 los elementos repetidos de la pila
            int x = origen.Tope();

            if (aux.Pertenece(x)) { // si el elemento esta en el conjunto aux (repetido)
                aux2.Agregar(x);    // agregar el elemento al conjunto aux2
            }
            aux.Agregar(x);
            origen.Desapilar();
        }
        return aux2;
    }

}