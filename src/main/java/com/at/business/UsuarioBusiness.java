package com.at.business;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.at.model.Rol;
import com.at.model.Usuario;
import com.at.model.persistence.RolRepository;
import com.at.model.persistence.UsuarioRepository;

@Service
public class UsuarioBusiness implements IUsuarioBusiness {

	@Autowired
	private RolRepository rolDAO;

	@Autowired
	private PasswordEncoder pe;

	@Autowired
	private UsuarioRepository usuarioDAO;

	@Override
	public Usuario load(long id) throws BusinessException, NotFoundException {
		Optional<Usuario> o;
		try {
			o = usuarioDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (o.isPresent())
			return o.get();
		else
			throw new NotFoundException();
	}

	@Override
	public Usuario add(Usuario usuario) throws BusinessException {
		try {
			List<Rol> rol = rolDAO.findByRol("ROLE_USER");
			Set<Rol> set = rol.stream().collect(Collectors.toSet());
			usuario.setRoles(set);
			usuario.setPassword(pe.encode(usuario.getPassword()));
			return usuarioDAO.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void delete(long id) throws BusinessException {
		try {
			usuarioDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Usuario update(Usuario usuario) throws BusinessException {
		try {
			return usuarioDAO.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public List<Usuario> list() throws BusinessException {
		try {
			return usuarioDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Usuario> list(String parteDelNombre) throws BusinessException {
		try {
			if (parteDelNombre != null && parteDelNombre.trim().length() > 2) {
				return usuarioDAO.findByFirstNameLikeAndEnabledTrueOrderByUsername("%" + parteDelNombre + "%");
			} else {
				throw new BusinessException("Parámetros incorrectos");
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Usuario load(String usernameOrEmail) throws BusinessException, NotFoundException {
		List<Usuario> l;
		try {
			l = usuarioDAO.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		if (l.size() == 0)
			throw new NotFoundException("No se encuentra el usuari@ con nombre/email=" + usernameOrEmail);

		return l.get(0);
	}


}

