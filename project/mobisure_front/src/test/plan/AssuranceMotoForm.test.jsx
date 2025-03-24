import { render, screen,fireEvent  } from '@testing-library/react';
import AssuranceMotoForm from '../../components/plans/components/formulaire/AssuranceMotoForm';
import '@testing-library/jest-dom'; 

describe("AssuranceMotoForm Component", () => {
  it("renders all form fields correctly", () => {
    render(<AssuranceMotoForm />);
    expect(screen.getByText("Formulaire Assurance Moto")).toBeInTheDocument();
    // Vérifier que les champs sont présents
    expect(screen.getByLabelText("Marque de moto :")).toBeInTheDocument();
    expect(screen.getByLabelText("Modèle de moto :")).toBeInTheDocument();
    expect(screen.getByText("Électrique ?")).toBeInTheDocument();
    expect(screen.getByLabelText("Année de fabrication :")).toBeInTheDocument();
    expect(screen.getByLabelText("Utilisation du véhicule :")).toBeInTheDocument();
    expect(screen.getByLabelText("Durée :")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument();
  });

  it("updates models when a marque is selected", () => {
    render(<AssuranceMotoForm />);
    // Sélectionner une marque
    const marqueSelect = screen.getByLabelText("Marque de moto :");
    fireEvent.change(marqueSelect, { target: { value: "Yamaha" } });
    // Vérifier que les modèles de Yamaha sont affichés
    const modelSelect = screen.getByLabelText("Modèle de moto :");
    expect(modelSelect).not.toBeDisabled();
    expect(screen.getByText("MT-07")).toBeInTheDocument();
    expect(screen.getByText("MT-09")).toBeInTheDocument();
  });

  it("updates selectedOption when a radio button is clicked", () => {
    render(<AssuranceMotoForm />);
    // Sélectionner "Oui" pour "Électrique"
    const ouiRadio = screen.getByLabelText("Oui");
    fireEvent.click(ouiRadio);
    expect(ouiRadio).toBeChecked();
    // Sélectionner "Non" pour "Électrique"
    const nonRadio = screen.getByLabelText("Non");
    fireEvent.click(nonRadio);
    expect(nonRadio).toBeChecked();
  });

  it("validates required fields", () => {
    const mockSetFormData = jest.fn();
    render(<AssuranceMotoForm formData={{ marque: "",modele: "",motorisation: "THERMIQUE",utilisation: "",duree: 0,fabrication: 0,plaque: "", options: []}}
      setFormData={mockSetFormData}
      />);
    const marqueSelect = screen.getByLabelText("Marque de moto :");
    fireEvent.change(marqueSelect, { target: { value: "Yamaha" } });
    // Soumettre le formulaire sans remplir les champs
    const submitButton = screen.getByRole('button', { name: /soumettre/i });
    fireEvent.click(submitButton);
    
    // Vérifier que les messages d'erreur sont affichés
    expect(screen.getByLabelText("Marque de moto :")).toBeValid();
    expect(screen.getByLabelText("Modèle de moto :")).toBeInvalid(); 
    expect(screen.getByLabelText("Année de fabrication :")).toBeInvalid(); 
    expect(screen.getByLabelText("Durée :")).toBeInvalid(); 
  });

  
});
