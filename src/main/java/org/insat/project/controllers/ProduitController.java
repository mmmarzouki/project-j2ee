package org.insat.project.controllers;

import org.insat.project.Repository.CategorieRepository;
import org.insat.project.Repository.ProduitRepository;
import org.insat.project.models.Categorie;
import org.insat.project.models.Produit;
import org.insat.project.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@Controller
public class ProduitController {


    public static String UPLOAD_FOLDER = "src/main/resources/static/img/";

    private void initFolder(){
        File folder = new File(UPLOAD_FOLDER);
        if(!folder.exists())
            folder.mkdir();
    }

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @RequestMapping(path = "/produit", method = RequestMethod.GET)
    public String showProduitDetails(Model model, @RequestParam(name = "id") int id, HttpSession session){
        Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
        if(utilisateur==null)
            return "login";

        Produit produit = produitRepository.findOne(id);
        initFolder();
        model.addAttribute("produit",produit);
        return "produit_details";
    }
    @RequestMapping(path = "/ajoutProduitForm", method = RequestMethod.GET)
    public String ajoutProduitForm(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        if(utilisateur==null)
            return "login";
        Produit produit = new Produit();
        initFolder();
        ArrayList<Categorie> categories = (ArrayList<Categorie>) categorieRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("produit",produit);
        return "produit_ajout";
    }
    @RequestMapping(path = "/ajoutProduit", method = RequestMethod.POST)
    public String ajoutProduit(Model model, Produit produit, @RequestParam("file") MultipartFile file){
            if (file.isEmpty()) {
                produit.setPicture("alt.png");
                produitRepository.save(produit);
                return "redirect:/administration";
            }

            try {

                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
               produit.setPicture(file.getOriginalFilename());

            } catch (IOException e) {
                produit.setPicture("alt.png");
                produitRepository.save(produit);
                return "redirect:/administration";
            }
            produitRepository.save(produit);
            return "redirect:/administration";
        }

    @RequestMapping(path = "/modifProduitForm", method = RequestMethod.GET)
    public String modifProduitForm(Model model, @RequestParam(name = "id") int id, HttpSession session){
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        if(utilisateur==null)
            return "login";
        Produit produit = produitRepository.findOne(id);
        model.addAttribute("produit",produit);
        ArrayList<Categorie> categories = (ArrayList<Categorie>) categorieRepository.findAll();
        model.addAttribute("categories",categories);
        return "produit_modif";

    }
}
