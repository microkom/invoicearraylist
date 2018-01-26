
package invoicearraylist;


public class Textos {
    public String errors(String errorRec){
        String error="";
        if (errorRec.equalsIgnoreCase("cantidad")){
            error= "\t*** La cantidad no puedo ser CERO ni negativa *** VER ==> ";
        }else if(errorRec.equalsIgnoreCase("precio")){
            error= "\t*** El precio no puede ser cero ni negativo *** VER ==> ";
        }else if(errorRec.equalsIgnoreCase("descuento")){
            error= "\t*** El descuento no puede ser negativo *** VER ==> ";
        }else{
            error="\tHay un error en la factura";
        }
       return error;
    }
    
    public String factura(){
        return "FACTURA DE VENTA";
    }
    public String hyphen(){
        String hyp="";
        for (int i=0;i<102;i++)hyp+=("-");
        return hyp;
    }
}
