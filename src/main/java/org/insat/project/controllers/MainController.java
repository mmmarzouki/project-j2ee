package org.insat.project.controllers;

import org.insat.project.Repository.ProduitRepository;
import org.insat.project.models.Produit;
import org.insat.project.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    ProduitRepository produitRepository;

    @RequestMapping("/")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("utilisateur")==null)
            return "redirect:/login";

        ArrayList<Produit> produits = (ArrayList<Produit>) produitRepository.findAll();
        model.addAttribute("produits",produits);
        return "index";
    }

    @RequestMapping("/administration")
    public String administration(Model model, HttpSession session){
        Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
        if(utilisateur==null)
            return "login";
        if(!utilisateur.isAdmin())
            return "index";

        ArrayList<Produit> produits= (ArrayList<Produit>)produitRepository.findAll();
        model.addAttribute("produits",produits);
        return "administration";
    }


    @RequestMapping(value = "/deconnecter",method = RequestMethod.GET)
    public String disconnect(HttpSession session){
        session.removeAttribute("utilisateur");
        return "redirect:/login";
    }
}
