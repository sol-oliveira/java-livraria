package br.ifsp.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import br.ifsp.livraria.entities.Usuario;
import br.ifsp.livraria.repository.UsuarioRepository;


@ManagedBean
public class AutenticarBean {
	
	private String login;
	private String senha;
	
	public String autenticar(){
	
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		
		UsuarioRepository usuarioRepository = 
				new UsuarioRepository(manager);
		
		manager.getTransaction().begin();
		
		Usuario usuario = usuarioRepository.busca(login, senha);
		
		factory.close();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (usuario != null) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession)ec.getSession(false);
			session.setAttribute("usuario", usuario);
			
			return "Principal";
		
		} else {
			
			FacesMessage fm = new FacesMessage("Usuário e/ou senha inválidos.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null,fm);
			
			return "Autenticar";
		}
		
	}
	
	public String registrarSaida(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		
		return "Autenticar";
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
