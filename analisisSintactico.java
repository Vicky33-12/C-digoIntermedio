package Intermedio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
//import analisisLexico;

public class analisisSintactico {
    //analisisLexico lexico = new analisisLexico();
    analisisLexico lexico = new analisisLexico();
    String columnas[] = {
                        "id", "num", "int", "float", "char", "," , ";", "+", "-", "*", "/", "(", ")", "$", "P", "Tipo","V","A","S","E","T","F","="
                        };
    String filas[] = {
                     "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37"
                     };
    
    String tabla[][] = {
        {/*Q0*/   "7", "", "4", "5", "6", "", "", "", "", "", "", "", "", "", "1", "2", "", "3", "", "", "", "", ""},
        {/*Q1*/   "", "", "", "", "", "", "", "", "", "", "", "", "", "P0", "", "", "", "", "", "", "", "", ""},
        {/*Q2*/   "8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q3*/   "", "", "", "", "", "", "", "", "", "", "", "", "", "P2", "", "", "", "", "", "", "", "", ""},
        {/*Q4*/   "P3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q5*/   "P4", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q6*/   "P5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q7*/   "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "9"},
        {/*Q8*/   "", "", "", "", "", "11", "12", "", "", "", "", "", "", "", "", "", "10", "", "", "", "", "", ""},
        {/*Q9*/   "20", "21", "", "", "", "", "", "14", "15", "", "", "19", "", "", "", "", "", "", "13", "16", "17", "18", ""},
        {/*Q10*/  "", "", "", "", "", "", "", "", "", "", "", "", "", "P1", "", "", "", "", "", "", "", "", ""},
        {/*Q11*/  "22", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q12*/  "7", "", "4", "5", "6", "", "", "", "", "", "", "", "", "", "23", "2", "", "3", "", "", "", "", ""},
        {/*Q13*/  "", "", "", "", "", "", "24", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q14*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "25", "17", "18", ""},
        {/*Q15*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "26", "17", "18", ""},
        {/*Q16*/  "", "", "", "", "", "", "P11", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q17*/  "", "", "", "", "", "", "P14", "P14", "P14", "29", "30", "", "P14", "", "", "", "", "", "", "", "", "", ""},
        {/*Q18*/  "", "", "", "", "", "", "P17", "P17", "P17", "P17", "P17", "", "P17", "", "", "", "", "", "", "", "", "", ""},
        {/*Q19*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "31", "17", "18", ""},
        {/*Q20*/  "", "", "", "", "", "", "P19", "P19", "P19", "P19", "P19", "", "P19", "", "", "", "", "", "", "", "", "", ""},
        {/*Q21*/  "", "", "", "", "", "", "P20", "P20", "P20", "P20", "P20", "", "P20", "", "", "", "", "", "", "", "", "", ""},
        {/*Q22*/  "", "", "", "", "", "11", "12", "", "", "", "", "", "", "", "", "", "32", "", "", "", "", "", ""},
        {/*Q23*/  "", "", "", "", "", "", "", "", "", "", "", "", "", "P7", "", "", "", "", "", "", "", "", ""},
        {/*Q24*/  "", "", "", "", "", "", "", "", "", "", "", "", "", "P8", "", "", "", "", "", "", "", "", ""},
        {/*Q25*/  "", "", "", "", "", "", "P9", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q26*/  "", "", "", "", "", "", "P10", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {/*Q27*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "33", "18", ""},
        {/*Q28*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "34", "18", ""},
        {/*Q29*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "35", ""},
        {/*Q30*/  "20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "36", ""},
        {/*Q31*/  "", "", "", "", "", "", "", "27", "28", "", "", "", "37", "", "", "", "", "", "", "", "", "", ""},
        {/*Q32*/  "", "", "", "", "", "", "", "", "", "", "", "", "", "P6", "", "", "", "", "", "", "", "", ""},
        {/*Q33*/  "", "", "", "", "", "", "P12", "P12", "P12", "29", "30", "", "p12", "", "", "", "", "", "", "", "", "", ""},
        {/*Q34*/  "", "", "", "", "", "", "P13", "P13", "P13", "29", "30", "", "p13", "", "", "", "", "", "", "", "", "", ""},
        {/*Q35*/  "", "", "", "", "", "", "P15", "P15", "P15", "P15", "P15", "", "P15", "", "", "", "", "", "", "", "", "", ""},
        {/*Q36*/  "", "", "", "", "", "", "P16", "P16", "P16", "P16", "P16", "", "P16", "", "", "", "", "", "", "", "", "", ""},
        {/*Q37*/  "", "", "", "", "", "", "P18", "P18", "P18", "P18", "P18", "", "P18", "", "", "", "", "", "", "", "", "", ""}
        
                     };
    
    String producciones[] = {
        "P' > P",
        "P > Tipo id V", 
        "P > A",
        "Tipo > int", 
        "Tipo > float",
        "Tipo > char",
        "V > , id V",
        "V > ; P",
        "A > id = S ;",
        "S > + E",
        "S  - E",
        "S > E",
        "E > E + T",
        "E > E - T",
        "E > T",
        "T > T * F",
        "T > T / F",
        "T > F",
        "F > ( E )",
        "F > id",
        "F > num"
        };
    
     String tipos[] = {"int","float","char"};
     int tablaSemanticaOperaciones[][] = 
                                            {{0,1,-1},
                                             {1,1,-1},
                                             {-1,-1,-1}};
     
     boolean tablaSemanticaAsignacion[][] = 
                                            {{true, true, false},
                                             {true, true, false},
                                             {false, false, true}};
     String entrada2 = "";
    Stack<String> pila = new Stack<>();
    Stack<Integer> pilaSemantica = new Stack<>();
    Stack<String> pilaOperadores = new Stack<>();
    Stack<String> pilaCodigoIntermedio = new Stack<>();
    Stack<String> aux = new Stack<>();
    String codigoIntermedio = "";
    List<String> variablesCodigoIntermedio = new ArrayList<>();
    int contarVariables = 0;
    String operacion = "";
    boolean errorSemantico;
    long totalPuntosYComas ;
    String mnsjErrSemantico;
    int filaErrorSemantico;
    boolean noDeclarada = true;
    String error = "";
    String cadena2 = "";
    String cambiosPilaSemantica = "";
    String cambiosPilaOperadores = "";
    public String  evaluar(String[] entrada, List<Integer> cantidadTokensFila, List<String> valores)
    {
        System.out.println("length entrada: "+entrada.length+" lenght valores: "+valores.size());
        valores.add("$");
        pila.push("$");
        pila.push("0");
        int columna = 0;
        int fila = 0;
        int cont = 0;  
        String contenidoPila = pila.toString()+"\n";
        entrada2+=entrada[cont]+"\n";
        cadena2 = cadena2+(valores.get(cont)+"\n");
        System.out.println("VALORES LONGITUD "+valores.size()+" valores: "+valores+" valores "+cont+": "+valores.get(cont));
  
        while(true)
        {
            try {
                fila = Integer.parseInt(pila.peek());
                columna = indiceColumnas(entrada[cont], columnas);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("CADENA NO ACEPTADA termino la cadena");
                Error(cont, cantidadTokensFila, 0);
                break;
            }catch(NumberFormatException e)
            {
                System.out.println("CADENA NO ACEPTADA cadena vacia");
                Error(cont, cantidadTokensFila, 0);
                break;

            }
            
            
            
            try {
                int valor = Integer.parseInt(tabla[fila][columna]);
                pila.push(entrada[cont]);
                pila.push(valor+"");
                //System.out.println("ENTRADA[CONT]: "+entrada[cont]+" cont: "+cont);
                
                System.out.println("VALOR: "+valores.get(cont));
               if(entrada[cont].equals("int") || entrada[cont].equals("float")|| entrada[cont].equals("char"))
               {
                   pilaCodigoIntermedio.add(valores.get(cont));
                   aux.add(valores.get(cont));
               }
               else if(entrada[cont].equals("id"))
               {
                   
                   if(!pilaCodigoIntermedio.isEmpty())
                   {
                      if(!pilaCodigoIntermedio.peek().equals(";"))
                        {
                            pilaCodigoIntermedio.add(valores.get(cont)+"");
                            aux.add(valores.get(cont));
                        } 
                   }
                   
                   
               }else if(entrada[cont].equals(";"))
               {
                   pilaCodigoIntermedio.add(valores.get(cont)+"");  
                   aux.add(valores.get(cont));
                   if(!llenarPilaOperadores(valores.get(cont))) break;
                   cambiosPilaOperadores+=pilaOperadores+"\n";
                   mostrarPilas();
               }
               else if(entrada[cont].equals("="))
               {
                   //aqui empiezo codigo intermedio
                   
                   System.out.println("FUUUUEEEEE AAAA: "+valores.get(cont-1));
                   codigoIntermedio(aux, entrada2);
                   llenarPilaSemantica(valores.get(cont-1));
                   cambiosPilaSemantica+=pilaSemantica+"\n";
                    
                   
                   mostrarPilas();
               }
               else
               {
                   if(!llenarPilaOperadores(valores.get(cont))) break;
                   cambiosPilaOperadores+=pilaOperadores+"\n";
                   mostrarPilas();
               }
                
                contenidoPila += pila.toString()+"\n";
                entrada2+=entrada[cont]+"\n";
                cadena2 = cadena2+(valores.get(cont)+"\n");
                cont++;
            } catch (NumberFormatException e) {
               // System.out.println("fila: "+fila+ " columna: "+columna);

                String valor = tabla[fila][columna];
                
                if(!valor.equals("P0"))
                {
                    if(!valor.equals(""))
                    {
                        int indiceProduccion = Integer.parseInt(valor.substring(1));
                        //System.out.println("VALOR: "+valor);
                        //System.out.println("PRODUCCION: "+indiceProduccion);
                        
                        for(int i = 2; i<producciones[indiceProduccion].split(" ").length; i++)
                        {
                            pila.pop();
                            pila.pop();

                        }
                        
                        fila = Integer.parseInt(pila.peek());
                        //System.out.println("fila: "+fila);
                        String terminalIdNum = producciones[indiceProduccion].split(" ")[2];
                        
                        // Contar el número total de puntos y comas en la pila
                        totalPuntosYComas = pilaCodigoIntermedio.stream().filter(elemento -> elemento.equals(";")).count();
                        // Eliminar todos los puntos y comas, excepto el último
                        pilaCodigoIntermedio.removeIf(elemento -> elemento.equals(";") && totalPuntosYComas-- > 1);

                        if(terminalIdNum.equals("id"))
                        {
                            if(producciones[indiceProduccion].split(" ")[0].equals("A"))
                            {
                                
                                filaErrorSemantico=cont;
                                while (true) {     
                                    if(!valores.get(filaErrorSemantico).equals("="))
                                    {
                                        filaErrorSemantico--;
                                    }else
                                    {
                                        filaErrorSemantico--;
                                        //System.out.println("VALOR DE IDEN A=S; "+valores.get(filaErrorSemantico--));
                                        
                                        HashSet<String> aux = new HashSet<>();
                                       
                                        
                                        //error+=valores.get(filaErrorSemantico)+"\n ";
                                        
                                         errorSemantico = false;
                                          noDeclarada=false;
                                        // Recorrer la lista y verificar si ya existe el elemento
                                        for (String elemento : pilaCodigoIntermedio) {
                                            
                                             mnsjErrSemantico = "";
                                            if (!aux.add(elemento) && !(elemento.equals("int")) && !(elemento.equals("char"))&& !(elemento.equals("float"))) { // add() devuelve false si el elemento ya está en el conjunto
                                                
                                                mnsjErrSemantico="variable duplicada: " + elemento;
                                                errorSemantico = true;
                                                break;
                                            }else if(valores.get(filaErrorSemantico).equals(elemento))
                                            {
                                                System.out.println("VARIABLE: "+valores.get(filaErrorSemantico)+" ELEMENTO: "+elemento);
                                                noDeclarada=true;
                                            }
                                            if(!noDeclarada)
                                            {
                                                mnsjErrSemantico="variable no declarada: "+valores.get(filaErrorSemantico); 
                                            }
                                        }
                                        break;
                                    }
                                    
                                }
                                
                            }
                            else
                            {    
                                System.out.println("FUE FFFFFFF: "+valores.get(cont-1));
                                llenarPilaSemantica(valores.get(cont-1));
                                contarVariables++;
                                variablesCodigoIntermedio.add("var"+contarVariables+ "="+valores.get(cont-1)+";");
                                codigoIntermedio+="var"+contarVariables+ "="+valores.get(cont-1)+";\n";
                                cambiosPilaSemantica+=pilaSemantica+"\n";
                                mostrarPilas();
                                HashSet<String> aux = new HashSet<>();
                                //error+=valores.get(filaErrorSemantico)+"\n ";
                                noDeclarada=false;
                                // Recorrer la lista y verificar si ya existe el elemento
                                for (String elemento : pilaCodigoIntermedio) {
                                    mnsjErrSemantico = "";
                                    
                                    if(valores.get(cont-1).equals(elemento))
                                    {
                                        System.out.println("VARIABLE: "+valores.get(cont-1)+" ELEMENTO: "+elemento);
                                        noDeclarada=true;
                                    }
                                    
                                    if(!noDeclarada)
                                    {
                                        mnsjErrSemantico="variable no declarada: "+valores.get(cont-1); 
                                    }
                                }  
                                System.out.println("valor en id: "+valores.get(cont-1)+" cont: "+(cont-1));
                               
                                
                            }
                            
                        }
                        else if(terminalIdNum.equals("num")){
                            System.out.println("valor en num: "+valores.get(cont-1)+" cont: "+(cont-1)); 
                            pilaSemantica.add(0);
                            contarVariables++;
                            variablesCodigoIntermedio.add("var"+contarVariables+ "="+valores.get(cont-1)+";");
                            codigoIntermedio+="var"+contarVariables+ "="+valores.get(cont-1)+";\n";
                            cambiosPilaSemantica+=pilaSemantica+"\n";
                        }
                        if (errorSemantico) 
                        {
                            System.out.println("se encontraron elementos duplicados.");
                            error+="Error semantico:\n"+mnsjErrSemantico;
                            break;
                        }else if(!noDeclarada)
                        {
                            error+="Error semantico:\n"+mnsjErrSemantico;
                            break;
                        }
                        
                        
                        System.out.println("produccion: "+producciones[indiceProduccion]);
                        System.out.println("produccion cortada: "+producciones[indiceProduccion].split(" ")[0]);
                        System.out.println("produccion terminal: "+producciones[indiceProduccion].split(" ")[2]);
                        System.out.println("");
                        columna = indiceColumnas(producciones[indiceProduccion].split(" ")[0], columnas);
                        pila.push(producciones[indiceProduccion].split(" ")[0]);
                       // System.out.println("fila: "+fila+" columna: "+columna);
                        pila.push(tabla[fila][columna]);
                        contenidoPila += pila.toString()+"\n";
                        entrada2+=entrada[cont]+"\n";
                        cadena2 = cadena2+(valores.get(cont)+"\n");
                    }
                    else
                    {
                        System.out.println("CADENA NO ACEPTADA lugar vacio");
                        
                        Error(cont, cantidadTokensFila, 0);
                        System.out.println("Error: "+error);
                        break; 
                    }
                    
                }
                else
                {
                   System.out.println("CADENA ACEPTADA");
                    break;
                }
            }
        }
        //System.out.println("CONTENIDO PILA:\n"+contenidoPila);
        //System.out.println("CANTIDAD TOKENS CON CONT:\n"+cont);
        System.out.println("PILA antes: "+pilaCodigoIntermedio);
        pilaCodigoIntermedio.removeIf(elemento -> elemento.equals(";"));
        System.out.println("PILA despues: "+pilaCodigoIntermedio);
        System.out.println("PILA SEMANTICA: "+pilaSemantica);
        
        System.out.println("PILA SEMANTICA COMPLETA:");
        System.out.println(cambiosPilaSemantica);
        System.out.println("PILA OPERADORES COMPLETA: "+pilaOperadores.size());
        System.out.println(cambiosPilaOperadores);
        
        for (String elemento : pilaCodigoIntermedio) {
            System.out.println(elemento);
        }
        System.out.println("ENTRADA:");
        System.out.println(entrada2);
        System.out.println("Valores:");
        System.out.println(cadena2);
        System.out.println("CODIGO INTERMEDIO: "+codigoIntermedio);
        System.out.println("PILA OPERADORES COMPLETA: ");
        System.out.println(cambiosPilaOperadores);
        return contenidoPila;
    }
    
    
    
    public String getCodigoIntermedio()
    {
        return codigoIntermedio;
    }
    
    
    private int indiceColumnas(String caracter, String[] columnas)
    {
        int indice = -1;
        //System.out.println("caracter: "+caracter);
        for(int j = 0; j<columnas.length; j++)
        {
            //System.out.println("J: "+j+ "   caracter: "+caracter+"    Columna:   "+columnas[j]);
            if(caracter.equals(columnas[j]))
            {
                indice = j;
               // System.out.println("##caracter: "+caracter+"   columna: "+columnas[j]);
                break;
            }
        }
        
        return indice;
    }
    
    private void Error(int cont, List<Integer> cantidadTokensFila, int num)
    {
      
        int g = 1;
        for (Integer elemento : cantidadTokensFila) {
            System.out.println("CONT: "+cont+" CANTRIDAD: "+cantidadTokensFila);
            if(cont>=elemento)
            {
                if(g==cantidadTokensFila.size())
                {
                    if(num == 0)
                    {
                        error+=("error sintactico en la fila: "+g);
                    }else
                    {
                        error+=("error semantico en la fila: "+g);
                    }
                    break;
                }
                else
                {
                    g++;
                    cont=cont-elemento; 
                }
                
            }
            else
            {
                if(num == 0)
                    {
                        error+=("error sintactico en la fila: "+g);
                    }else
                    {
                        error+=("error semantico en la fila: "+g);
                    }
                break;
            }
                   
        }
    }
    
  
    public String getError()
    {
        System.out.println("ERROR EN GET ERROR: "+error);
        return error;
    }
    
    public void llenarPilaSemantica(String id)
    {
        
        int indiceId = pilaCodigoIntermedio.indexOf(id);
        
        
        
        // Recorre la pila hacia la izquierda, buscando el primer tipo encontrado
        for (int i = indiceId - 1; i >= 0; i--) {
            String elemento = pilaCodigoIntermedio.get(i);
            System.out.println("pilaCOdigoIntermedio: "+pilaCodigoIntermedio);
            System.out.println("elemento: "+elemento);
            // Comprueba si el elemento es un tipo válido
            if (elemento.equals("int") || elemento.equals("char") || elemento.equals("float")) {
                System.out.println(id + ", " + elemento);
                switch (elemento) {
                    case "int":
                        pilaSemantica.add(0);
                        break;
                    case "float":
                        pilaSemantica.add(1);
                        break;
                    case "char":
                        pilaSemantica.add(2);
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            }
        }

    }
    
    public void mostrarPilas()
    {
        System.out.println("PILA SEMANTICA COMPLETA:");
        System.out.println(cambiosPilaSemantica);
        System.out.println("PILA OPERADORES COMPLETA:");
        System.out.println(cambiosPilaOperadores);
    }
    
    public boolean llenarPilaOperadores(String operador)
    {
        System.out.println("OPERADORRRRRR>: "+operador);
        
            
        
        switch (operador) {
            case "+":
                if(!pilaOperadores.isEmpty()){
                    while(pilaOperadores.peek().equals("+") || pilaOperadores.peek().equals("-") || pilaOperadores.peek().equals("/") || pilaOperadores.peek().equals("*"))
                    {
                        operacion=" "+pilaOperadores.peek()+" ";
                        if(!operacionSemantica()) return false;
                        if(pilaOperadores.isEmpty()) break;
                        
                    }
                    pilaOperadores.push(operador);
                }
                else
                {
                    pilaOperadores.push(operador);
                }
                
                break;
            case "-":
                if(!pilaOperadores.isEmpty()){
                   while(pilaOperadores.peek().equals("+") || pilaOperadores.peek().equals("-") || pilaOperadores.peek().equals("/") || pilaOperadores.peek().equals("*"))
                    {
                        operacion=" "+pilaOperadores.peek()+" ";
                        if(!operacionSemantica()) return false;
                        if(pilaOperadores.isEmpty()) break;
                        
                    }
                    pilaOperadores.push(operador);
                }
                else
                {
                    pilaOperadores.push(operador);
                }
                break;
            case "/":
                if(!pilaOperadores.isEmpty()){
                   while(pilaOperadores.peek().equals("/") || pilaOperadores.peek().equals("*"))
                    {
                        operacion=" "+pilaOperadores.peek()+" ";
                        if(!operacionSemantica()) return false;
                        if(pilaOperadores.isEmpty()) break;
                        
                    }
                    pilaOperadores.push(operador);
                }
                else
                {
                    pilaOperadores.push(operador);
                }
                break;
            case "*":
                if(!pilaOperadores.isEmpty()){
                   while(pilaOperadores.peek().equals("/") || pilaOperadores.peek().equals("*"))
                    {
                        operacion=" "+pilaOperadores.peek()+" ";
                        if(!operacionSemantica()) return false;
                        if(pilaOperadores.isEmpty()) break;
                        
                    }
                    pilaOperadores.push(operador);
                }
                else
                {
                    pilaOperadores.push(operador);
                }
                break;
            case "(":
                pilaOperadores.push("(");
                break;
            case ")":
                if(!pilaOperadores.isEmpty())
                {
                while(!pilaOperadores.peek().equals("("))
                {
                    operacion=" "+pilaOperadores.peek()+" ";
                    if(!operacionSemantica()) return false;
                    
                }
                pilaOperadores.pop();
                }
                break;
            case ";":
                while(!pilaOperadores.isEmpty())
                {
                    operacion=" "+pilaOperadores.peek()+" ";
                    if(!operacionSemantica()) return false;
                    
                }
                System.out.println("PILA SEMANTICA DESPUES DE ; "+pilaSemantica+" pila semantica sixe: "+pilaSemantica.size());
                
                if(pilaSemantica.size()==2){
                if(!tablaSemanticaAsignacion[pilaSemantica.pop()][pilaSemantica.pop()])
                {
                    error+="Error semantico tipos imcompatibles en asignación";
                    System.out.println("Error semantico tipos imcompatibles en asignación \n");
                    return false;
                }
                }
                break;
        }
        return true;
    }
    
    
    public boolean operacionSemantica()
    {
        
        System.out.println("SEMANTICA EN OPERACION SEMANTICA: "+pilaSemantica);
        int v1 = pilaSemantica.pop();
        int v2 = pilaSemantica.pop();
        System.out.println("valor 1: "+v1);
        System.out.println("Valor 2: "+v2);
        
        
        
            if(tablaSemanticaOperaciones[v1][v2]!=-1)
            {
                System.out.println("va1 y v2: "+v1+", "+v2);
                System.out.println("valor tabla: "+tablaSemanticaOperaciones[v1][v2]);
                pilaSemantica.push(tablaSemanticaOperaciones[v1][v2]);
                pilaOperadores.pop();
                
                System.out.println("semantico: "+cambiosPilaSemantica);
                System.out.println("opercaiones: "+cambiosPilaOperadores);
                System.out.println("variables: "+variablesCodigoIntermedio.toString()+" longitud: "+variablesCodigoIntermedio.size());
                
                String valorA = variablesCodigoIntermedio.get(variablesCodigoIntermedio.size()-1);           
                String valorB = variablesCodigoIntermedio.get(variablesCodigoIntermedio.size()-2);
                
                variablesCodigoIntermedio.remove(variablesCodigoIntermedio.size()-1);
                codigoIntermedio+= valorB.substring(0,4)+" = "+valorB.substring(0,4)+operacion+valorA.substring(0,4)+";\n";
                contarVariables = contarVariables-1;
                
                mostrarPilas();
                System.out.println("operacion semantica: "+v1+", "+v2+" pila: "+pilaSemantica);   
                return true;
            }
            else{
                System.out.println("va1 y v2: "+v1+", "+v2);
                System.out.println("valor tabla: "+tablaSemanticaOperaciones[v1][v2]);
                error+="Error semantico operacion incompatible \n";
                System.out.println("Error semantico operacion incompatible \n");
                return false;
            }
            
    }
    
    
    public void codigoIntermedio(Stack<String> pila2, String variable)
    {
        
        Stack<String> invertida = new Stack<>();
         while (!pila2.isEmpty()) {
            invertida.push(pila2.pop());
        }
        System.out.println("aux: "+invertida);
        
        String valor = "";
        String tipo = "";
        while(!invertida.isEmpty())
        {
            System.out.println("size: "+invertida.size());
            valor = invertida.pop();
            System.out.println("valor pilacodigoi: "+valor);
            if(!valor.equals(";"))
            {
                
                System.out.println("valor pilacodigoi: "+valor);
                switch (valor) {
                    case "int":
                        tipo = "int";
                        break;
                    case "float":
                        tipo = "float";
                        break;
                    case "char":
                        tipo = "char";
                        break;
                    default:
                        switch (tipo) {
                            case "int":
                                codigoIntermedio += "int "+ valor+";\n";
                                break;
                            case "float":
                                codigoIntermedio += "float "+ valor+";\n";;
                            break;
                            case "char":
                                codigoIntermedio += "char "+ valor+";\n";
                            break;
                        }
                        

                }
            }
            
            }
        System.out.println("codigo intermedio: "+codigoIntermedio);
        
    }
    
    public static void main(String[] args) {
        String entrada1[] = {"id","=","(","num","+","num",")",";","$"};
        //String entrada[] = {"id","=","num",";","$"};
        String entrada2[] = {"char","id",",","id",",","id",";","id","=","num","+","(","num","-","id",")",";","$"};

        analisisSintactico analizador = new analisisSintactico();
 
    }
}
