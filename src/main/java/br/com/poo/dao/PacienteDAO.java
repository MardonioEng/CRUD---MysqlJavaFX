package br.com.poo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.poo.model.Paciente;

public class PacienteDAO {

	private static PacienteDAO instance;
	private EntityManager entityManager;
	
	public static PacienteDAO getInstance() {
		if(instance == null) {
			instance = new PacienteDAO();
		}
		return instance;
	}
	
	public PacienteDAO() {
		entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("meuPU");
		if(entityManager == null) {
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}
	
	public Paciente getById(final int id) {
		return entityManager.find(Paciente.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findAll() {
		return entityManager.createQuery("FROM " + Paciente.class.getSimpleName()).getResultList();
	}
	
	public void persist(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void merge(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remove(Paciente paciente) {
		try {
			entityManager.getTransaction().begin();
			paciente = entityManager.find(Paciente.class, paciente.getId());
			entityManager.remove(paciente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final int id) {
		try {
			Paciente paciente = this.getById(id);
			this.remove(paciente);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
