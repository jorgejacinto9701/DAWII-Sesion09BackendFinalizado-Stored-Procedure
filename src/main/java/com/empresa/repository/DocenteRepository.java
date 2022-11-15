package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	

	@Query("select x from Docente x where (?1 is '' or x.nombre like ?1) and (?2 is '' or x.dni = ?2) and (?3 is -1 or x.ubigeo.idUbigeo = ?3) and x.estado = ?4")       
	public List<Docente> listaDocentePorNombreDniUbigeo(String nombre, String dni, int idUbigeo, int estado);
	
	@Query(value = "CALL sp_lista_docente_like(?1)", nativeQuery = true )
	public List<Docente> listaPorNombreLike(String nombre);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "CALL sp_inserta_docente(?1,?2,?3,?4)", nativeQuery = true )
	public void insertaDocente(String nombre, String dni, int idUbigeo, int estado);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "CALL sp_actualiza_docente(?1,?2,?3,?4,?5)", nativeQuery = true )
	public void actualizaDocente(String nombre, String dni, int idUbigeo, int estado, int idDocente);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "CALL sp_elimina_docente(?1)", nativeQuery = true )
	public void eliminaDocente(int idDocente);
	
	@Query(value = "CALL sp_busca_docente(?1)", nativeQuery = true )
	public Docente buscaDocentePK(int idDocente);
	
	@Query(value = "CALL sp_busca_ultimo_docente()", nativeQuery = true )
	public Docente buscaUltimoDocente();
	
	
	
}


