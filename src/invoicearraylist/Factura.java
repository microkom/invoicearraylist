package invoicearraylist;

import static java.lang.Math.floor;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Iterator;


public class Factura {
    private int numero;
    private Cliente cliente;
    private Fecha fecha;
    //private LineaFactura[] lineas = new LineaFactura[10];
    ArrayList<LineaFactura> lineas = new ArrayList<LineaFactura>();
    
    Textos texto = new Textos();
    
    public Factura(int numero, Cliente cliente, Fecha fecha){
        this.numero=numero;
        this.cliente=cliente;
        this.fecha=fecha;
    }
    
    public int getNumero(){return this.numero;}
    public Cliente getCliente(){return this.cliente;}
    public Fecha getFecha(){return this.fecha;}
    public ArrayList<LineaFactura> getLineas(){return this.lineas;}
    
    public void setCliente(Cliente cliente){this.cliente=cliente;}
    public void setFecha(Fecha fecha){this.fecha=fecha;}
    public void setLineaFactura(ArrayList<LineaFactura> lineas){this.lineas=lineas;}
    
    /*
    nuevaLinea(LineaFactura lf): Añadir una línea a la factura. En caso
    que lo haya podido añadir debe devolver el número de la linea en la
    que la ha insertado. En caso contrario (vector lleno) debe devolver -
    1. 
    */
    
    Iterator<LineaFactura> it = lineas.iterator();
    LineaFactura e = null;
    
    while (lineas.hasNext()) {
            e = it.next();
            System.out.println(e.);
    }
    public int nuevaLinea(ArrayList<LineaFactura> lf){
        int vector=-1;
        int i,j;
        boolean insertado=false;
        
        j=buscarProducto(lf.getProducto().getNombre());
        
        //comprobación de que el producto ya aparece en la factura o no 
        if (j!=-1){
            //suma de cantidades 
            this.lineas[j].setCantidad(lineas[j].getCantidad()+lf.getCantidad());
            this.lineas[j].setDescuento(lf.getDescuento());
        }else{
        for (i=0;i<this.lineas.length && insertado==false;i++){
            if(this.lineas[i]==null){
                lineas[i]=lf;
                insertado=true;
                vector=i;
            }
        }
        }
        return vector;
    }

    //borrarLineaNumero(int numLinea) : Elimina la linea con el número indicado
    public void borrarLineaNumero(int numLinea){
        //borrado del vector recibiendo la posición exacta 
        this.lineas[numLinea]=null;
        
        //agrupar el contenido del vector al inicio
        ordenVector();
    }
    
    //Agrupación del vector al inicio cuando tiene espacios blancos
    public void ordenVector(){
        for(int i=0;i<lineas.length;i++){
            //buscar lineas que no están vacías
            if(lineas[i]!=null){
                boolean found=false;

                //búsqueda del primer null en el array y reemplazo con el último not null
                for(int j=0;j<lineas.length && found==false;j++){
                    if(lineas[j]==null){
                        lineas[j]=lineas[i];
                        lineas[i]=null;
                        found=true;
                    }
                }
            }
        }
    }
    
    /*
    borrarLineaNombre(String nombre): Elimina la linea con el producto indicado 
    (buscar por nombre de producto).                                        */
    public void borrarLineaNombre(String nombre){
        boolean encontrado=false;
        for(int i=0;i<this.lineas.length && encontrado==false;i++){
             //saltarse las campos del vector vacio
            if(lineas[i]!=null){
                //busqueda del nombre recibido por parametro con el vector actual en i
                if (this.lineas[i].getProducto().getNombre().equals(nombre)){
                    //borrado del contenido de una casilla del vector
                    this.lineas[i]=null;
                    encontrado=true;
                }
            } 
        }
        ordenVector();
    }
    
    //importeTotal(): Calcular el importe total.
//importeTotal(): Calcular el importe total.
    public double importeTotal(){
        double total=0;int i;
        for (i=0;i<this.lineas.length;i++){
            //saltarse las campos del vector vacio
            
            if(this.lineas[i]!=null){
                //sumar los precios de todos los productos
                total+=(this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad());
            }
        }                
        return total;
    }
    public double descuentoTotal(){
        double desc=0;int i;
        for (i=0;i<this.lineas.length;i++){
            //saltarse las campos del vector vacio
            if(this.lineas[i]!=null){
                //sumar los descuentos de todos los productos
                desc+=this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad()*this.lineas[i].getDescuento();
                
            }
        }                
        return desc;
    }
    
    //ImporteTotalImpuestos(): Devuelve el importe total con el iva(21%).
    public double importeTotalImpuestos(){
        //calculo del importe total más el IVA
        return (importeTotal()-descuentoTotal())*1.21;
    }
    
    /*
    buscarProducto(String nombre): Buscar si una factura tiene un
    determinado el producto indicado en una de sus lineas. Debe
    devolver el número de linea en caso de encontrarlo o -1 en caso de
    que no.
    */
    public int buscarProducto(String nombre){
        int numLinea=-1,i;
        boolean found=false;
        for(i=0;i<lineas.length && found==false;i++){
            //saltarse las campos del vector vacio
            if(lineas[i]!=null){
                //comparar los nombres del producto con el que han pasado por parametro
                if (lineas[i].getProducto().getNombre().equalsIgnoreCase(nombre)){
                    //asignar el valor de la linea donde se encontró el nombre a la variable
                    numLinea=i;
                    //variable de control para salir del bucle
                    found=true;
                }
            }
        }
        return numLinea;
    }
    
    /* 
    imprimir(): Método para imprimir los datos de la factura Debe incluir número de factura, 
    fecha en formato corto, datos del cliente, listado de productos e importe antes y 
    después de impuestos         */
    public String imprimir(){
        String prod="",impreso1="",impreso2="";
        int i;
        boolean error=false;
        for (i=0;i<this.lineas.length;i++){
            
            //saltarse las campos del vector vacio
            if(this.lineas[i]!=null){
                
                //descuentos individuales
                double desc=this.lineas[i].getDescuento()*this.lineas[i].getProducto().getPrecio();
                
                //descuentos aplicados por producto
                double descAplic=this.lineas[i].getProducto().getPrecio()-desc;
                
                //listado de productos con detalle de precio unitario, descuentos y subtotal
                
                //control de cantidades iguales o menores que cero
                if (this.lineas[i].getCantidad()<=0){
                    error=true;
                    prod+=texto.errors("cantidad");
                    prod+=this.lineas[i].getProducto().getDescripcion()+"\n";
                    
                //control de precios iguales o menores que cero
                }else if(this.lineas[i].getProducto().getPrecio()<=0){
                    error=true;
                    prod+=texto.errors("precio");
                    prod+=this.lineas[i].getProducto().getDescripcion()+"\n";
                
                //control de descuentos negativos
                }else if(this.lineas[i].getDescuento()<0){
                    error=true;
                    prod+=texto.errors("descuento");
                    prod+=this.lineas[i].getProducto().getDescripcion()+"\n";
                }else{    
                prod+="\t "+(i+1)+"\t"+this.lineas[i].getCantidad()+"\t  "+ //nº de item y cantidad
                        this.lineas[i].getProducto().getPrecio()+"\t      "+ //precio por unidad
                        this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad()+"\t"+ //precio 
                        this.lineas[i].getDescuento()*100+"\t"+             //descuento en porcentaje
                        desc*this.lineas[i].getCantidad()+"\t"+             //descuento en euros
                        descAplic*this.lineas[i].getCantidad()+"\t";        //subtotal cuenta por unidad
                        
                prod+=this.lineas[i].getProducto().getDescripcion()+"\n";   //Descripcion del producto
                }
            }
        }
       
        impreso1=   "\n\n\n\n"
                    + "\t"+texto.hyphen()+"\n\t"+
                    texto.factura()+"\n\t"+
                    texto.hyphen()+"\n"+
                    "\tNº "+this.numero+"\t"+
                    "\tFecha: "+fecha.corta()+
                    "\tNIF: "+cliente.getNif()+"\n"+
                    "\tNombre: "+cliente.getNombre()+"\t"+
                    "Dirección: "+cliente.getDireccion()+"     "+
                    "Teléfono: "+cliente.getTelefono()+"\n\t"+
                    texto.hyphen()+"\n"+
                    "\tPRODUCTOS\n\t"+
                    texto.hyphen()+"\n"+
                    "\tItem.  Cant.     Precio U.    Precio   Dto-%\tDto-€\tSubt    Descripción:\n";
        if (error==false){
            //valores a imprimir si no hay errores en la factura
            impreso2=   prod+        
                        "\t"+
                        texto.hyphen()+"\n"+
                        "\t\tSutotal: \t"+importeTotal()+" €\n"+
                        "\t\tDesc: \t\t"+descuentoTotal()+" €\n"+
                        "\t\tSutotal: \t"+(importeTotal()-descuentoTotal())+" €\n"+
                        "\t\tIVA: \t\t"+(importeTotal()-descuentoTotal())*0.21+" €\n"+
                        "\t\tTotal: \t\t"+importeTotalImpuestos()+" €\n\n"+
                       "\t"+texto.hyphen()+"\n";
       }else{
           //No se muestran valores hasta que no se solucionen errores de cantidad o precio, iguales a cero o negat.
            impreso2=   prod+        
                        "\t"+texto.hyphen()+"\n"+
                        "\t\tSutotal: \t===  €\n"+
                        "\t\tDesc: \t\t===  €\n"+
                        "\t\tSutotal: \t===  €\n"+
                        "\t\tIVA: \t\t===  €\n"+
                        "\t\tTotal: \t"+texto.errors("")+" \n"+" \n"+
                        "\t"+texto.hyphen()+"\n";
       }
    return impreso1+impreso2;
    }
}