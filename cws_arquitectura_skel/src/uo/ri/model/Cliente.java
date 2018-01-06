package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uo.ri.model.types.Address;

@Entity
@Table(name = "TCLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	@Embedded
	private Address address;
	private String telefono;
	private String email;

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	@OneToMany(mappedBy = "cliente")
	private Set<MedioPago> mediosPago = new HashSet<>();
	
	@OneToMany(mappedBy = "recomendado")
	private Set<Recomendacion> recomendaciones = new HashSet<>();
	@OneToOne(mappedBy = "recomendador")
	private Recomendacion recomendacion_recibida;

	Cliente() {
	}

	public Cliente(String dni) {
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Cliente(String dni, String nombre, String apellidos, String telefono, String email) {
		this(dni,nombre,apellidos);
		this.telefono = telefono;
		this.email = email;
	}

	public Cliente(String dni, String nombre, String apellidos, String telefono, String email, Address address) {
		this(dni, nombre, apellidos,telefono,email);
		this.address = address;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getId() {
		return id;
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

	public String getPhone() {
		return telefono;
	}

	public void setPhone(String telefono) {
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

	Set<Recomendacion> _getRecomendacionesHechas() {
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
		for (Vehiculo vehiculo : vehiculos) {
			for (Averia averia : vehiculo.getAverias()) {
				if (averia.esElegibleParaBono3()) {
					listaAveriasBono3NoUsadas.add(averia);
				}
			}
		}

		return listaAveriasBono3NoUsadas;
	}

}
