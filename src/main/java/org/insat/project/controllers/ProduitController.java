package org.insat.project.controllers;

import org.insat.project.Repository.ProduitRepository;
import org.insat.project.models.Produit;
import org.insat.project.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProduitController {

    @Autowired
    ProduitRepository produitRepository;

    @RequestMapping(path = "/produit", method = RequestMethod.GET)
    public String showProduitDetails(Model model, @RequestParam(name = "id") int id, HttpSession session){
        Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
        if(utilisateur==null)
            return "login";

        Produit produit = produitRepository.findOne(id);
        model.addAttribute("produit",produit);
        return "produit_details";
    }
}
