package co.edu.uptc.presenter;

import co.edu.uptc.model.Department;
import co.edu.uptc.model.Inhabitant;
import co.edu.uptc.model.Municipality;
import co.edu.uptc.view.View;

public class Presenter {

	private View view;
	private Department department;

	public Presenter() {
		view = new View();
		department = new Department();
		loadData();
	}

	public void loadData() {
		Municipality municipality1 = new Municipality("Tunja");
		municipality1.addInhabitant(new Inhabitant("Pedro"));
		municipality1.addInhabitant(new Inhabitant("Maria"));
		municipality1.addInhabitant(new Inhabitant("Luisa"));
		municipality1.addInhabitant(new Inhabitant("Sara"));
		municipality1.addInhabitant(new Inhabitant("Juan"));
		department.addMunicipality(municipality1);

		Municipality municipality2 = new Municipality("Duitama");
		municipality2.addInhabitant(new Inhabitant("Luis"));
		municipality2.addInhabitant(new Inhabitant("Carlos"));
		municipality2.addInhabitant(new Inhabitant("Andres"));
		department.addMunicipality(municipality2);

		Municipality municipality3 = new Municipality("Sogamoso");
		municipality3.addInhabitant(new Inhabitant("Ana"));
		municipality3.addInhabitant(new Inhabitant("Sofia"));
		municipality3.addInhabitant(new Inhabitant("Camila"));
		department.addMunicipality(municipality3);

		Municipality municipality4 = new Municipality("Paipa");
		municipality4.addInhabitant(new Inhabitant("Julian"));
		municipality4.addInhabitant(new Inhabitant("Esteban"));
		municipality4.addInhabitant(new Inhabitant("David"));
		department.addMunicipality(municipality4);

	}

	public int showMenu() {
		view.showMessage("================= MUNICIPIOS ===================");
		view.showMessage("1. Agregar municipio");
		view.showMessage("2. Agregar habitante");
		view.showMessage("3. Listar habitantes de un municipio");
		view.showMessage("4. Municipio con mayor número de habitantes");
		view.showMessage("5. Salir");

		int option = Integer.parseInt(view.readData());
		return option;
	}

	public void addMunicipality() {
		view.showMessage("Ingrese el nombre del municipio");
		String name = view.readData();
		Municipality municipality = new Municipality(name);
		department.addMunicipality(municipality);
	}

	public void addInhabitant() {
		view.showMessage("Ingrese el nombre del municipio");
		String municipalityName = view.readData();
		Municipality municipality = department.getMunicipality(municipalityName);
		if (municipality == null) {
			view.showMessage("El municipio no existe, se creará uno nuevo");
			department.addMunicipality(new Municipality(municipalityName));
			municipality = department.getMunicipality(municipalityName);
			view.showMessage("Ingrese el nombre del habitante");
			String inhabitantName = view.readData();
			Inhabitant inhabitant = new Inhabitant(inhabitantName);
			municipality.addInhabitant(inhabitant);
		} else {
			view.showMessage("Ingrese el nombre del habitante");
			String inhabitantName = view.readData();
			Inhabitant inhabitant = new Inhabitant(inhabitantName);
			if (!municipality.findInhabitant(inhabitant)) {
				municipality.addInhabitant(inhabitant);
			} else {
				view.showMessage("El habitante ya existe");
			}
		}
	}

	public void listInhabitants() {
		if (department.getMunicipalities().isEmpty()) {
			view.showMessage("No hay municipios agregados.");
			return;
		}

		view.showMessage("Ingrese el nombre del municipio");
		String municipalityName = view.readData();
		Municipality municipality = department.getMunicipality(municipalityName);
		if (municipality != null) {
			if (municipality.inOrderHabitants().isEmpty()) {
				view.showMessage("No hay habitantes en el municipio");
			} else {
				for (Inhabitant inhabitant : municipality.inOrderHabitants()) {
					view.showMessage(inhabitant.getName());
				}
			}
		} else {
			view.showMessage("El municipio no existe");
		}
	}

	public void municipalityWithMostInhabitants() {
		Municipality municipality = department.municipalityWithMostInhabitants();
		view.showMessage("El municipio con más habitantes es " + municipality.getNameMunicipality());
	}

	public void run() {
		int option = 0;
		do {
			option = showMenu();
			switch (option) {
				case 1:
					addMunicipality();
					break;
				case 2:
					addInhabitant();
					break;
				case 3:
					listInhabitants();
					break;
				case 4:
					municipalityWithMostInhabitants();
					break;
				case 5:
					view.showMessage("Hasta luego");
					break;
				default:
					view.showMessage("Opción inválida");
					break;
			}
		} while (option != 5);
	}

	public static void main(String[] args) {
		Presenter presenter = new Presenter();
		presenter.run();
	}

}
