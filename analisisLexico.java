package Intermedio;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class analisisLexico {
    
    String reservadas[] = {"int", "float","char"};
    Pattern operadores = Pattern.compile("[\\+\\-\\*/]");
    Pattern simbolos = Pattern.compile("[=;,()]");
    Pattern digitos = Pattern.compile("[0123456789]");
    Pattern letra = Pattern.compile("[a-zA-Z]");
    Pattern id = Pattern.compile("([a-zA-Z]+)([0-9]*)");
    String error = "";
    int cantidad = 0;
    List<Integer> cantidadtokens = new ArrayList<>();
    public List<String> valores = new ArrayList<>();
    public List analizadorLexico(String cadena)
    {
        
        List<String> tokens = new ArrayList<>();
        
        //System.out.println("CADENAAAAAAAAAA:  "+cadena);
        String token = "";
        int indice = 0;
        int cont = 1;
        
        while(true)
        {
           
           try{
               
            Matcher mat = letra.matcher(cadena.charAt(indice)+"");
           //System.out.println("indice: "+indice+" caracter: "+cadena.charAt(indice)+" token: "+token);
           if (mat.matches()) {
                token += cadena.charAt(indice);
                indice++;
            } 
            else 
            {
                if(cadena.charAt(indice)==' ')
                {
                    if(!esReservada(token, tokens, indice, cadena))
                    {
                        esIdONum(mat, token, tokens);
                    }
                    
                    token="";
                    indice++;
                    
                }
                else
                {
                    mat = digitos.matcher(cadena.charAt(indice)+"");
                    if (mat.matches()) 
                    {
                        
                        if(!esReservada(token, tokens, indice, cadena))
                        {
                            token += cadena.charAt(indice);
                        }
                        indice++;
                    } 
                    else 
                    {
                        mat = operadores.matcher(cadena.charAt(indice)+"");
                        if (mat.matches()) 
                        {
                            if(!esReservada(token, tokens, indice, cadena))
                            {
                                esIdONum(mat, token, tokens);
                            }
                            switch (cadena.charAt(indice)+"") {
                                case "+":
                                    tokens.add("+");
                                    valores.add("+");
                                    cantidad++;
                                    break;
                                case "-":
                                    tokens.add("-");
                                    valores.add("-");
                                    cantidad++;
                                    break;
                                case "*":
                                    tokens.add("*");
                                    valores.add("*");
                                    cantidad++;
                                    break;
                                case "/":
                                    tokens.add("/");
                                    valores.add("/");
                                    cantidad++;
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            token="";
                            indice++;
                        } 
                        else 
                        {
                            mat = simbolos.matcher(cadena.charAt(indice)+"");
                            if (mat.matches()) {
                                   
                                if(!esReservada(token, tokens, indice, cadena))
                                {
                                    esIdONum(mat, token, tokens);
                                }
                                switch (cadena.charAt(indice)+"") {
                                case "=":
                                    tokens.add("=");
                                    valores.add("=");
                                    cantidad++;
                                    break;
                                case ";":
                                    tokens.add(";");
                                    valores.add(";");
                                    cantidad++;
                                    break;
                                case ",":
                                    tokens.add(",");
                                    valores.add(",");
                                    cantidad++;
                                    break;
                                case "(":
                                    tokens.add("(");
                                    valores.add("(");
                                    cantidad++;
                                    break;
                                case ")":
                                    tokens.add(")");
                                    valores.add(")");
                                    cantidad++;
                                    break;
                                default:
                                    throw new AssertionError();
                                }
                                token="";
                                indice++;
                            } 
                            else 
                            {
                               
                            
                                if((cadena.charAt(indice)+"".length())==13 || (cadena.charAt(indice)+"".length())==10)
                                {
                                    //System.out.println("es salto de linea");
                                    if((cadena.charAt(indice)+"".length())==10)
                                    {
                                        cantidadtokens.add(cantidad);
                                        cantidad = 0;
                                    }
                                    
                                    if((cadena.charAt(indice)+"".length())==13 ) cont++;
                                    if(!esReservada(token, tokens, indice, cadena))
                                    {
                                        token += cadena.charAt(indice);
                                    }
                                    token="";
                                    indice++;
                                }
                                else
                                {
                                    error += "CARACTER NO VALIDO: "+cadena.charAt(indice)+" en la fila: "+cont;
                                    break;
                                }
                                
                              
                                
                            }
                        }
                    }
                        
                }
            }
           }catch(IndexOutOfBoundsException e)
           {
               //System.out.println("fin de la cadena");
               break;
           }
        } 
        int d = 0;
        //System.out.println("Cantidad de tokens leng: "+cantidadtokens.size());
        System.out.println("VALORES LONGITUD "+valores.size()+" valores: "+valores);
        for (String elemento : tokens) {
            //System.out.println("indice: "+d+"elemento: "+elemento);
            d++;
        }
        return tokens;
    }
        

    public String getError()
    {
        return error;
    }
    
    public List getCantidadTokensFila()
    {
        return cantidadtokens;
    }
    
     public List getValores()
    {
        
        return valores;
    }
    
    private boolean esReservada(String token, List tokens, int indice, String cadena)
    {
        boolean ban = false;
        for(int i = 0; i<reservadas.length; i++)
        {
            if(token.equals(reservadas[i]))
            {
                tokens.add(token);
                valores.add(token);
                cantidad++;
                token = "";
                ban = true;
                break;
            }
        }                                     
        return ban;
    }
        
    private void esIdONum(Matcher mat, String token, List tokens)
    {
        mat = id.matcher(token);
        if(mat.matches())
        {
           // System.out.println("FUE ID token: "+token+" longitud: "+token.length());
            tokens.add("id");
            valores.add(token);
            cantidad++;
        }
        else if(token.length()!=0)
        {
            tokens.add("num");
            valores.add(token);
            cantidad++;
        }
    }
 
    public static void main(String[] args) {
        String cadena = "char valor5 = (15+20); ";
        //String entrada[] = {"id","=","num",";","$"};
        String cadena2 = "num2=(5+5);";
         String cadena3 = "char  id,id,id;id=122+(12-id);";
        analisisLexico analizador = new analisisLexico();
    
        // Llamar al método evaluar con el arreglo de entrada
        analizador.analizadorLexico(cadena3);
    }
}
