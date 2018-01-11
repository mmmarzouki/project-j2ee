package org.insat.project.controllers;

import org.insat.project.Repository.UtilisateurRepository;
import org.insat.project.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @RequestMapping(value = "/loginController",method = RequestMethod.POST)
    public String logIn(Model model,Utilisateur utilisateurInput, HttpSession session){
        if(utilisateurInput==null){
            return "login";
        }
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndPasswd(utilisateurInput.getEmail(),utilisateurInput.getPasswd());
        if(utilisateur==null)
            return "login";
        session.setAttribute("utilisateur",utilisateur);
        model.addAttribute("utiilisateur",utilisateur);
        if(utilisateur.isAdmin())
            return "redirect:/administration";
        return "redirect:/";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String log(Model model,HttpSession session){
        if(session.getAttribute("utilisateur")!=null)
            return "redirect:/";

        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur",utilisateur);
        return "login";
    }
}
