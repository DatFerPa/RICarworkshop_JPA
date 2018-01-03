package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uo.ri.model.types.Address;

public class Cliente {

	private String dni;
	private String nombre;
	private String apellidos;
	private Address address;
	private String telefono;
	private String email;
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	private Set<MedioPago> mediosPago = new HashSet<>();
	private Set<Recomendacion> recomendaciones = new HashSet<>();
	private Recomendacion recomendacion_recibida;

	public Cliente(String dni) {
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Cliente(String dni, String nombre, String apellidos, String telefono, String email, Address address) {
		this(dni, nombre, apellidos);
		this.setTelefono(telefono);
		this.email = email;
		this.address = address;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>(vehiculos);
	}

	Set<Vehiculo> _getVehiculos() {

		return vehiculos;
	}

	public Set<MedioPago> getMediosPago() {
		return new HashSet<>(mediosPago);
	}

	Set<MedioPago> _getMediosPago() {
		return mediosPago;
	}
	
	public Set<Recomendacion> getRecomendacionesHechas() {
		return new HashSet<>(recomendaciones);
	}
	
	Set<Recomendacion> _getRecomendacionesHechas(){
		return recomendaciones;
	}
	
	public Recomendacion getRecomendacionRecibida() {
		return recomendacion_recibida;
	}

	 void _setRecomendacionRecibida(Recomendacion recomendacion_recibida) {
		this.recomendacion_recibida = recomendacion_recibida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", address=" + address + "]";
	}

	public List<Averia> getAveriasBono3NoUsadas() {
		List<Averia> listaAveriasBono3NoUsadas = new ArrayList<>();
		for(Vehiculo vehiculo: vehiculos) {
			for(Averia averia:vehiculo.getAverias()) {
				if(averia.esElegibleParaBono3()) {
					listaAveriasBono3NoUsadas.add(averia);
				}
			}
		}
		
		return listaAveriasBono3NoUsadas;
	}

	

	

}
