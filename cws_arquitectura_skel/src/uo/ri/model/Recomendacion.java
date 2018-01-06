package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TRECOMENDACIONES", uniqueConstraints = {
		@UniqueConstraint(columnNames = "RECOMENDADOR_ID, RECOMENDADO_ID") })
public class Recomendacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cliente recomendador;
	@ManyToOne
	private Cliente recomendado;
	private boolean usada_bono;

	Recomendacion() {
	}

	public Recomendacion(Cliente recomendador, Cliente recomendado) {
		super();

		Association.Recomendar.link(recomendador, this, recomendado);
	}

	public Cliente getRecomendado() {
		return recomendado;
	}

	void _setRecomendado(Cliente recomendado) {
		this.recomendado = recomendado;
	}

	public Cliente getRecomendador() {
		return recomendador;
	}

	void _setRecomendador(Cliente recomendador) {
		this.recomendador = recomendador;
	}

	public boolean isUsada() {
		return usada_bono;
	}

	public void setUsada(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	public void unlink() {
		Association.Recomendar.unlink(recomendador, this, recomendado);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recomendado == null) ? 0 : recomendado.hashCode());
		result = prime * result + ((recomendador == null) ? 0 : recomendador.hashCode());
		result = prime * result + (usada_bono ? 1231 : 1237);
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
		Recomendacion other = (Recomendacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recomendado == null) {
			if (other.recomendado != null)
				return false;
		} else if (!recomendado.equals(other.recomendado))
			return false;
		if (recomendador == null) {
			if (other.recomendador != null)
				return false;
		} else if (!recomendador.equals(other.recomendador))
			return false;
		if (usada_bono != other.usada_bono)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recomendacion [recomendador=" + recomendador + ", recomendado=" + recomendado + ", usada_bono="
				+ usada_bono + "]";
	}

}
