package utp.edu.pe.bfc.models;

public class Iventario {
    private int inventarioId;
    private int productoId;
    private Producto producto;
    private int stock;

    public Iventario(int productoId, Producto producto, int stock) {
        this.productoId = productoId;
        this.producto = producto;
        this.stock = stock;
    }

    public Iventario(int inventarioId, int productoId, Producto producto, int stock) {
        this.inventarioId = inventarioId;
        this.productoId = productoId;
        this.producto = producto;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Iventario{" +
                "inventarioId=" + inventarioId +
                ", productoId=" + productoId +
                ", producto=" + producto +
                ", stock=" + stock +
                '}';
    }

    public int getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
