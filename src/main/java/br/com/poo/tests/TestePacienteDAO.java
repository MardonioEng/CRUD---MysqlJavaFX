package br.com.poo.tests;

import br.com.poo.dao.PacienteDAO;
import br.com.poo.model.Paciente;

public class TestePacienteDAO {
	public static void main(String[] args) {
		
		Paciente paciente1 = new Paciente(null, "Mardonio", 20);
		Paciente paciente2 = new Paciente(null, "Rodrigues", 25);
		Paciente paciente3 = new Paciente(null, "Silva", 30);
		
		PacienteDAO.getInstance().persist(paciente1);
		PacienteDAO.getInstance().persist(paciente2);
		PacienteDAO.getInstance().persist(paciente3);
		
//		List<Paciente> pacientes = PacienteDAO.getInstance().findAll();
//		
//		pacientes.stream().forEach(System.out::println);
//		
	}
}
