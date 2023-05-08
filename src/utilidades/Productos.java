package utilidades;

public class Productos {
	
	private int id;
	private String descripcion;
	private String codigoBarra;
	private int precioCompra;
	private int precioVenta;
	
	
	public Productos() {
		this.id = 0;
		this.descripcion = "";
		this.codigoBarra = "";
		this.precioCompra = 0;
		this.precioVenta = 0;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public int getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}
	public int getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}
	

}
