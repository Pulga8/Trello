package com.at;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.at.model.Lista;
import com.at.model.Rol;
import com.at.model.Tablero;
import com.at.model.Tarea;
import com.at.model.Usuario;
import com.at.model.persistence.ListaRepository;
import com.at.model.persistence.RolRepository;
import com.at.model.persistence.TableroRepository;
import com.at.model.persistence.TareaRepository;
import com.at.model.persistence.UsuarioRepository;

@Service
public class DefaultData {

	@Autowired
	private UsuarioRepository userDAO;
	@Autowired
	private RolRepository roleDAO;
	@Autowired
	private PasswordEncoder pe;
	@Autowired
	private TableroRepository tableroDAO;
	@Autowired
	private ListaRepository listaDAO;
	@Autowired
	private TareaRepository tareaDAO;

	public Usuario ensureUserIntegration() {
		List<Usuario> l = userDAO.findByUsername("integracion");
		if (l.size() == 0) {
			Usuario u = new Usuario();
			u.setAccountNonExpired(true);
			u.setAccountNonLocked(true);
			u.setCredentialsNonExpired(true);
			u.setEmail("integration@mail.com");
			u.setEnabled(true);
			u.setFirstName("Integracion");
			u.setLastName("System");
			u.setPassword(pe.encode("passintegracion"));
			u.setUsername("integracion");
			Set<Rol> sr = new HashSet<Rol>();
			sr.add(ensureRoleIntegration());
			u.setRoles(sr);
			return userDAO.save(u);
		} else {
			return l.get(0);
		}
	}

	public Usuario ensureUserGetToken() {
		List<Usuario> l = userDAO.findByUsername("gettoken");
		if (l.size() == 0) {
			Usuario u = new Usuario();
			u.setAccountNonExpired(true);
			u.setAccountNonLocked(true);
			u.setCredentialsNonExpired(true);
			u.setEmail("gettoken@mail.com");
			u.setEnabled(true);
			u.setFirstName("OnlyGetToken");
			u.setLastName("System");
			u.setPassword(pe.encode("g3tt0k3n$"));
			u.setUsername("gettoken");
			Set<Rol> sr = new HashSet<Rol>();
			sr.add(ensureRoleTokenRequest());
			u.setRoles(sr);
			return userDAO.save(u);
		} else {
			return l.get(0);
		}

	}

	public void ensureAllRoles() {
		ensureRoleIntegration();
		ensureRoleAdmin();
		ensureRoleUser();
		ensureRoleTokenRequest();
	}

	public Rol ensureRoleIntegration() {
		return ensureRole("ROLE_INTEGRATION", "Solo integración");
	}

	public Rol ensureRoleAdmin() {
		return ensureRole("ROLE_ADMIN", "Administrador");
	}

	public Rol ensureRoleUser() {
		return ensureRole("ROLE_USER", "Usuario");
	}

	public Rol ensureRoleTokenRequest() {
		return ensureRole("ROLE_TOKEN_REQUEST", "Para requerir tokens de integracion");
	}

	private Rol ensureRole(String role, String descripcion) {
		List<Rol> l = roleDAO.findByRol(role);
		if (l.size() == 0) {
			Rol r = new Rol();
			r.setRol(role);
			return roleDAO.save(r);
		} else {
			return l.get(0);
		}
	}

	public Tablero ensureTableroIntegration() {
		List<Tablero> l = tableroDAO.findByNombre("integracionTablero");
		if (l.size() == 0) {
			Tablero t = new Tablero();
			t.setNombre("integracionTablero");
			t.setAdministrador(userDAO.findByUsername("integracion").get(0));
			t.setPosicion(0);
			t.setFechaCreacion(Date.valueOf(LocalDate.now()));
			t.setFondo("ff0000");
			t.setFavorito(false);
			Set<Usuario> miembros = new HashSet<Usuario>(userDAO.findByUsername("integracion"));
			t.setMiembros(miembros);
			return tableroDAO.save(t);
		} else {
			return l.get(0);
		}
	}

	@Transactional
	public Lista ensureListaIntegration() {
		List<Tablero> tabList = tableroDAO.findByNombre("integracionTablero");
		List<Lista> l = listaDAO.findByTablero_Id(tabList.get(0).getId());
		if (l.isEmpty()) {
			Lista i = new Lista();
		//	i.setTipoLista("integracionLista");
			i.setPosicion(0);
			i.setTablero(tabList.get(0));
			i = listaDAO.save(i);
			return i;
		} else {
			return l.get(0);
		}
	}

	@Transactional
	public Tarea ensureTareaIntegration() {
		List<Tablero> tabList = tableroDAO.findByNombre("integracionTablero");
		List<Lista> lis = listaDAO.findByTablero_Id(tabList.get(0).getId());
		List<Tarea> tar = tareaDAO.findByLista_Id(lis.get(0).getId());

		if (tar.isEmpty()) {
			Tarea i = new Tarea();
			i.setNombre("integracionTarea");
			i.setDescripcion("descripcion");
			i.setPrioridad(0);
			i.setCreadaPor(userDAO.findByUsername("integracion").get(0));
			i.setFechaDeCreacion(Date.valueOf(LocalDate.now()));
			i.setFechaDeUltimaModificacion(Date.valueOf(LocalDate.now()));
//			Set<Usuario> miembrosAsignados = new HashSet<Usuario>(userDAO.findByUsername("integracion"));
//			i.setMiembrosAsignados(miembrosAsignados);
			i.setLista(lis.get(0));

			i = tareaDAO.save(i);
			return i;
		} else {
			return tar.get(0);
		}
	}

}
