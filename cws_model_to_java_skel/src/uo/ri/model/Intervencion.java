package uo.ri.model;

public class Intervencion {

	private Averia averia;
	private Mecanico mecanico;
	private int minutos;

	public Intervencion(Mecanico mecanico, Averia averia) {
		super();
		Association.Intervenir.link(averia, this, mecanico);
	}

	void _setAveria(Averia averia) {
		this.averia = averia;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averia == null) ? 0 : averia.hashCode());
		result = prime * result + ((mecanico == null) ? 0 : mecanico.hashCode());
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
		Intervencion other = (Intervencion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals(other.averia))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intervencion [averia=" + averia + ", mecanico=" + mecanico + ", minutos=" + minutos + "]";
	}

	public Averia getAveria() {
		return averia;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	

}