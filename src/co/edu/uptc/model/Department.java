package co.edu.uptc.model;

import java.util.ArrayList;

import co.edu.uptc.structures.BinaryTree;

public class Department {
  private BinaryTree<Municipality> municipalities;

  public Department() {
    this.municipalities = new BinaryTree<>((m1, m2) -> m1.getNameMunicipality().compareTo(m2.getNameMunicipality()));
  }

  public void addMunicipality(Municipality municipality) {
    municipalities.insert(municipality);
  }

  public boolean findMunicipality(Municipality municipality) {
    return municipalities.find(municipality);
  }

  public Municipality getMunicipality(String municipalityName) {
    return municipalities.search(new Municipality(municipalityName));
  }

  public Municipality municipalityWithMostInhabitants() {
    ArrayList<Municipality> municipalities = this.municipalities.preOrder();
    Municipality municipalityWithMostInhabitants = municipalities.get(0);

    for (Municipality municipality : municipalities) {
      if (municipality.inOrderHabitants().size() > municipalityWithMostInhabitants.inOrderHabitants().size()) {
        municipalityWithMostInhabitants = municipality;
      }
    }

    return municipalityWithMostInhabitants;
  }

  public ArrayList<Municipality> getMunicipalities() {
    return municipalities.inOrder();
  }

}
