import { render, screen,fireEvent  } from '@testing-library/react';
import AssuranceVehiculeForm from '../../components/plans/components/formulaire/AssuranceVehiculeForm';
import '@testing-library/jest-dom'; 

// Activer le mock
jest.mock('react-router-dom')

describe("AssuranceVehiculeForm Component", () => {
  const formData = {
    marque: "",
    model: "",
    electrique: "",
    annee: "",
    utilisation: "",
    duree: "",
  };

  const handleSubmit = jest.fn();
  const setFormData = jest.fn();
  const setModalVisible = jest.fn();

  it("renders all form fields correctly", () => {
    render(
      <AssuranceVehiculeForm
        formData={formData}
        setFormData={setFormData}
        handleSubmit={handleSubmit}
        isModalVisible={false}
        setModalVisible={setModalVisible}
      />
    );

    // Vérifier que le titre du formulaire est affiché
    expect(screen.getByText("Formulaire Assurance Véhicule")).toBeInTheDocument();

    // Vérifier que les champs sont présents
    expect(screen.getByLabelText("Marque de véhicule :")).toBeInTheDocument();
    expect(screen.getByLabelText("Modèle de véhicule :")).toBeInTheDocument();
    expect(screen.getByText("Électrique ?")).toBeInTheDocument();
    expect(screen.getByLabelText("Année de fabrication :")).toBeInTheDocument();
    expect(screen.getByLabelText("Utilisation du véhicule :")).toBeInTheDocument();
    expect(screen.getByLabelText("Durée :")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument(); 
  });

  it("updates models when a marque is selected", () => {
    render(
      <AssuranceVehiculeForm
        formData={formData}
        setFormData={setFormData}
        handleSubmit={handleSubmit}
        isModalVisible={false}
        setModalVisible={setModalVisible}
      />
    );

    // Sélectionner une marque
    const marqueSelect = screen.getByLabelText("Marque de véhicule :");
    fireEvent.change(marqueSelect, { target: { value: "Audi" } });

    // Vérifier que les modèles d'Audi sont affichés
    const modelSelect = screen.getByLabelText("Modèle de véhicule :");
    expect(modelSelect).not.toBeDisabled();
    expect(screen.getByText("A3")).toBeInTheDocument();
    expect(screen.getByText("A4")).toBeInTheDocument();
  });


  it("validates required fields", () => {
    render(
      <AssuranceVehiculeForm
        formData={formData}
        setFormData={setFormData}
        handleSubmit={handleSubmit}
        isModalVisible={false}
        setModalVisible={setModalVisible}
      />
    );
    const marqueSelect = screen.getByLabelText("Marque de véhicule :");
    fireEvent.change(marqueSelect, { target: { value: "Audi" } });

    const submitButton = screen.getByRole('button', { name: /soumettre/i });
    fireEvent.click(submitButton);
    expect(screen.getByLabelText("Marque de véhicule :")).toBeValid();
    expect(screen.getByLabelText("Modèle de véhicule :")).toBeInvalid();
    expect(screen.getByLabelText("Année de fabrication :")).toBeInvalid();
    expect(screen.getByLabelText("Utilisation du véhicule :")).toBeInvalid();
    expect(screen.getByLabelText("Durée :")).toBeInvalid();
  });
});