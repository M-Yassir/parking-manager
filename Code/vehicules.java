public abstract class vehicules {
    protected String marque ;
    protected String modele;
    protected String immatriculation ;
    

    public vehicules(String marque,String modele,String immatriculation) {
        this.marque = marque;
        this.modele  = modele;
        this.immatriculation = immatriculation;

    }


    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;

    }
}
