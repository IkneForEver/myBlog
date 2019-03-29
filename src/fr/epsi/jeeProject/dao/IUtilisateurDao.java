package fr.epsi.jeeProject.dao;

import java.util.List;


import fr.epsi.jeeProject.beans.Utilisateur;

public interface IUtilisateurDao<E, ID> {
	
	public void create(Utilisateur u);
	
	public void update(Utilisateur u);

	public void remove(Utilisateur u);
	
	public Utilisateur findByEmail(String e);	
}
