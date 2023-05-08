package utilidades;

public class Clientes {
	
	private int id;
	private String cedula;
	private String nombre;
	private String apellido;
	private String telefono;
	
	public Clientes() {
		this.id=0;
		this.cedula="";
		this.nombre="";
		this.apellido="";
		this.telefono="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
