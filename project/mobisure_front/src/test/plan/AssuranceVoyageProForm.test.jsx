import { render, screen,fireEvent } from '@testing-library/react';
import VoyageProfessionnelForm from '../../components/plans/components/formulaire/VoyageProForm';
import '@testing-library/jest-dom'; // Import jest-dom

describe("VoyageProfessionnelForm Component", () => {
  it("renders all form fields correctly", () => {
    render(<VoyageProfessionnelForm />);
    // Vérifier que le titre du formulaire est affiché
    expect(screen.getByText("Formulaire pour Voyage Professionnel")).toBeInTheDocument();
    // Vérifier que les champs sont présents
    expect(screen.getByLabelText("Nom de l'entreprise :")).toBeInTheDocument();
    expect(screen.getByLabelText("Destination :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Depart :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Retour :")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument();
  });

  it("updates form data when input fields are changed", () => {
    render(<VoyageProfessionnelForm />);
    const companyNameInput = screen.getByLabelText("Nom de l'entreprise :");
    fireEvent.change(companyNameInput, { target: { value: "Ma Société" } });
    expect(companyNameInput).toHaveValue("Ma Société");
    const destinationInput = screen.getByLabelText("Destination :");
    fireEvent.change(destinationInput, { target: { value: "Paris" } });
    expect(destinationInput).toHaveValue("Paris");

    const dateDepartInput = screen.getByLabelText("Date Depart :");
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
    expect(dateDepartInput).toHaveValue("2023-10-01");
    
    const dateRetourInput = screen.getByLabelText("Date Retour :");
    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });
    expect(dateRetourInput).toHaveValue("2023-10-10");
  });

  it("submits the form with correct data", () => {
    const handleSubmit = jest.fn();
    render(<VoyageProfessionnelForm onSubmit={handleSubmit} />);
  
    // Remplir le formulaire
    const companyNameInput = screen.getByLabelText("Nom de l'entreprise :");
    fireEvent.change(companyNameInput, { target: { value: "Ma Société" } });
  
    const destinationInput = screen.getByLabelText("Destination :");
    fireEvent.change(destinationInput, { target: { value: "Paris" } });
  
    const dateDepartInput = screen.getByLabelText("Date Depart :");
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
  
    const dateRetourInput = screen.getByLabelText("Date Retour :");
    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });
  
    // Soumettre le formulaire
    const submitButton = screen.getByRole('button', { name: /soumettre/i });
    fireEvent.click(submitButton);
  
    // Vérifier que la fonction handleSubmit est appelée avec les bonnes données
    expect(handleSubmit).toHaveBeenCalledWith({
      companyName: "Ma Société",
      destination: "Paris",
      dateDepart: "2023-10-01",
      dateRetour: "2023-10-10",
    });
  });
});