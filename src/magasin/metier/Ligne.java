/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * classe métier de gestion des lignes de commande
 *
 * @author Michel Poriaux
 * @version 1.0
 * @see ComFact
 * @see Produit
 */

public class Ligne {
    /**
     * produit commandé sur cette ligne
     */
    protected Produit produit;
    /**
     * quantité commandée
     */
    protected int quantite;
    /**
     * prix du produit commandé au moent de l'achat
     */
    protected BigDecimal prixAchat;

    /**
     * constructeur par défaut
     */
    public Ligne() {
    }

    /**
     *constructeur paramétré
     * @param produit produit commandé
     * @param quantite quantité commandée
     * @param prixAchat prix du produit au moment de l'achat
     */

    public Ligne(Produit produit, int quantite, BigDecimal prixAchat) {

        this.produit = produit;
        this.quantite = quantite;
        this.prixAchat = prixAchat;
        prixAchat.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * getter produit
     * @return produit commandé
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * setter produit
     * @param produit commandé
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * getter quantité
     * @return quantité commandée
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * setter quantité
     * @param quantite commandée
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * getter prix achat
     * @return prix achat
     */
    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    /**
     * setter prix achat
     * @param prixAchat prix achat
     */
    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    /**
     * description complète
     * @return  description complète
     */
    @Override
    public String toString() {
        return "Ligne{" + "produit=" + produit + ", quantite=" + quantite + ", prixAchat=" + prixAchat + '}';
    }

    /**
     * test d'égalité basé sur le produit
     * @param o autre ligne de commande
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ligne ligne = (Ligne) o;
        return produit.equals(ligne.produit);
    }

    /**
     * hashcode basé sur le hashcode du produit
     * @return hashcode de la ligne
     */
    @Override
    public int hashCode() {
        return Objects.hash(produit);
    }
}
