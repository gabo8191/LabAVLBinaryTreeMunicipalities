package co.edu.uptc.model;

import java.util.ArrayList;

import co.edu.uptc.structures.AVLTree;

public class Municipality {
  private String nameMunicipality;
  private AVLTree<Inhabitant> inhabitants;

  public Municipality(String nameMunicipality) {
    this.nameMunicipality = nameMunicipality;
    this.inhabitants = new AVLTree<>((i1, i2) -> i1.getName().compareTo(i2.getName()));
  }

  public String getNameMunicipality() {
    return nameMunicipality;
  }

  public void setNameMunicipality(String nameMunicipality) {
    this.nameMunicipality = nameMunicipality;
  }

  public void addInhabitant(Inhabitant inhabitant) {
    inhabitants.insert(inhabitant);
  }

  public ArrayList<Inhabitant> inOrderHabitants() {
    return inhabitants.inOrder();
  }

  public boolean findInhabitant(Inhabitant inhabitant) {
    return inhabitants.find(inhabitant);
  }

}
