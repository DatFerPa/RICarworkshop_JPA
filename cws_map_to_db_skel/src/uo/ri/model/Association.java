package uo.ri.model;

public class Association {

	public static class Poseer {

		public static void link(Cliente cliente, Vehiculo vehiculo) {
			vehiculo._setCliente(cliente);

			cliente._getVehiculos().add(vehiculo);
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo) {
			cliente._getVehiculos().remove(vehiculo);

			vehiculo._setCliente(null);
		}

	}

	public static class Clasificar {

		public static void link(TipoVehiculo tipo, Vehiculo vehiculo) {
			vehiculo._setTipo(tipo);

			tipo._getVehiculos().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipo, Vehiculo vehiculo) {
			tipo._getVehiculos().remove(vehiculo);

			vehiculo._setTipo(null);
		}

	}

	public static class Pagar {

		public static void link(MedioPago medioPago, Cliente cliente) {
			medioPago._setCliente(cliente);

			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(MedioPago medioPago, Cliente cliente) {
			cliente._getMediosPago().remove(medioPago);

			medioPago._setCliente(null);
		}
	}

	public static class Averiar {

		public static void link(Vehiculo vehiculo, Averia averia) {
			averia._setVehiculo(vehiculo);

			vehiculo._getAverias().add(averia);
		}

		public static void unlink(Vehiculo vehiculo, Averia averia) {
			vehiculo._getAverias().remove(averia);

			averia._setVehiculo(null);
		}

	}

	public static class Facturar {

		public static void link(Averia averia, Factura factura) {
			averia._setFactura(factura);

			factura._getAverias().add(averia);
		}

		public static void unlink(Averia averia, Factura factura) {
			factura._getAverias().remove(averia);

			averia._setFactura(null);
		}

	}

	public static class Cargar {

		public static void link(Factura factura, Cargo cargo, MedioPago medioPago) {
			cargo._setFactura(factura);
			cargo._setMedioPago(medioPago);

			factura._getCargos().add(cargo);
			medioPago._getCargos().add(cargo);
		}

		public static void unlink(Cargo cargo) {
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);

			cargo._setMedioPago(null);
			cargo._setFactura(null);

		}
	}

	public static class Asignar {

		public static void link(Mecanico mecanico, Averia averia) {
			averia._setMecanico(mecanico);

			mecanico._getAsignadas().add(averia);
		}

		public static void unlink(Mecanico mecanico, Averia averia) {
			mecanico._getAsignadas().remove(averia);

			averia._setMecanico(null);
		}

	}

	public static class Intervenir {

		public static void link(Averia averia, Intervencion intervencion, Mecanico mecanico) {
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);

			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);
		}

		public static void unlink(Averia averia, Intervencion intervencion, Mecanico mecanico) {
			averia._getIntervenciones().remove(intervencion);
			mecanico._getIntervenciones().remove(intervencion);

			intervencion._setAveria(null);
			intervencion._setMecanico(null);
		}

		public static void unlink(Intervencion intervencion) {
			intervencion.getAveria()._getIntervenciones().remove(intervencion);
			intervencion.getMecanico()._getIntervenciones().remove(intervencion);

			intervencion._setAveria(null);
			intervencion._setMecanico(null);
		}

	}

	public static class Sustituir {

		public static void link(Repuesto repuesto, Sustitucion sustitucion, Intervencion intervencion) {
			sustitucion._setRepuesto(repuesto);
			sustitucion._setIntervencion(intervencion);

			repuesto._getSustituciones().add(sustitucion);
			intervencion._getSustituciones().add(sustitucion);
		}

		public static void unlink(Repuesto repuesto, Sustitucion sustitucion, Intervencion intervencion) {
			repuesto._getSustituciones().remove(sustitucion);
			intervencion._getSustituciones().remove(sustitucion);

			sustitucion._setRepuesto(null);
			sustitucion._setIntervencion(null);
		}

		public static void unlink(Sustitucion sustitucion) {
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);
			sustitucion.getIntervencion()._getSustituciones().remove(sustitucion);

			sustitucion._setRepuesto(null);
			sustitucion._setIntervencion(null);
		}

	}

	public static class Recomendar {
		public static void link(Cliente recomendador, Recomendacion recomendacion, Cliente recomendado) {
			recomendacion._setRecomendado(recomendado);
			recomendacion._setRecomendador(recomendador);

			recomendado._setRecomendacionRecibida(recomendacion);
			recomendador._getRecomendacionesHechas().add(recomendacion);
		}

		public static void unlink(Cliente recomendador, Recomendacion recomendacion, Cliente recomendado) {
			recomendado._setRecomendacionRecibida(null);
			recomendador._getRecomendacionesHechas().remove(recomendacion);

			recomendacion._setRecomendado(null);
			recomendacion._setRecomendador(null);
		}
	}

}
