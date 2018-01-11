package org.insat.project.Repository;

import org.insat.project.models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository <Utilisateur,Integer> {
    public Utilisateur findByEmailAndPasswd(String email, String passwd);
}
