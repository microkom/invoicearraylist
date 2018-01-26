package invoicearraylist;

public class LineaFactura {
    private Producto producto;
    private int cantidad;
    private double descuento;
    
    public LineaFactura(Producto producto, int cantidad){
        this.producto=producto;
        this.cantidad=cantidad;
        this.descuento=0;
    }
    public LineaFactura(Producto producto, int cantidad,double descuento){
        this.producto=producto;
        this.cantidad=cantidad;
        this.descuento=descuento/100;
    }
    
    public Producto getProducto(){return this.producto;}
    public int getCantidad(){return this.cantidad;}
    public double getDescuento(){return this.descuento;}

    public void setProducto(Producto producto){this.producto=producto;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}
    public void setDescuento(double descuento){this.descuento=descuento;}
}
