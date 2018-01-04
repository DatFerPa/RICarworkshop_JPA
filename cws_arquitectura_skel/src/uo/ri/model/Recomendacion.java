package uo.ri.model;

public class Recomendacion {

	private Cliente recomendador;
	private Cliente recomendado;
	private boolean usada_bono;

	public Recomendacion(Cliente recomendador, Cliente recomendado) {
		super();
		
		Association.Recomendar.link(recomendador,this,recomendado);
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

	public boolean isUsada_bono() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	public void unlink() {
		Association.Recomendar.unlink(recomendador, this, recomendado);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recomendado == null) ? 0 : recomendado.hashCode());
		result = prime * result + ((recomendador == null) ? 0 : recomendador.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Recomendacion [recomendador=" + recomendador + ", recomendado=" + recomendado + ", usada_bono="
				+ usada_bono + "]";
	}
	
	

}
