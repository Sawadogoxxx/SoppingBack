package com.springmapping.springmapping.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DynamicUpdate
@Table
@NoArgsConstructor @AllArgsConstructor
public class Categorie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30,nullable = false,unique = true)
    private String nomCategorie;

    @OneToMany(mappedBy = "categorie",cascade ={
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Product> produits;

    //ajouter un produit a la liste des produits
    public  void addProduct(Product product){
        if(produits==null){
            produits=new ArrayList<>();
        }
        this.produits.add(product);
        product.setCategorie(this);
    }
}
