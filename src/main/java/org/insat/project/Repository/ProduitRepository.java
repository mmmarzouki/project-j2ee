package org.insat.project.Repository;

import org.insat.project.models.Produit;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository extends CrudRepository<Produit,Integer> {
}
