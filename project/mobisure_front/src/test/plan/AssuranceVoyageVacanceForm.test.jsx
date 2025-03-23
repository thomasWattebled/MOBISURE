import { render, screen,fireEvent } from '@testing-library/react';
import VoyageVacanceForm from '../../components/plans/components/formulaire/VoyageVacanceForm';
import '@testing-library/jest-dom'; // Import jest-dom

describe("VoyageProfessionnelForm Component", () => {
  it("renders all form fields correctly", () => {
    render(<VoyageVacanceForm />);
    // Vérifier que le titre du formulaire est affiché
    expect(screen.getByText("Formulaire pour Voyage Professionnel")).toBeInTheDocument();
    // Vérifier que les champs sont présents
    expect(screen.getByLabelText("Pays de depart")).toBeInTheDocument();
    expect(screen.getByLabelText("Destination :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Depart :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Retour :")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument();
  });

  it("updates form data when input fields are changed", () => {
    render(<VoyageVacanceForm />);
    const companyNameInput = screen.getByLabelText("Pays de depart");
    fireEvent.change(companyNameInput, { target: { value: "France" } });
    expect(companyNameInput).toHaveValue("France");
    const destinationInput = screen.getByLabelText("Destination :");
    fireEvent.change(destinationInput, { target: { value: "Londres" } });
    expect(destinationInput).toHaveValue("Londres");

    const dateDepartInput = screen.getByLabelText("Date Depart :");
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
    expect(dateDepartInput).toHaveValue("2023-10-01");
    
    const dateRetourInput = screen.getByLabelText("Date Retour :");
    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });
    expect(dateRetourInput).toHaveValue("2023-10-10");
  });


  it("submits the form with correct data", () => {
    const handleSubmit = jest.fn();
    render(<VoyageVacanceForm onSubmit={handleSubmit} />);
  
    // Remplir le formulaire
    const PaysNameInput = screen.getByLabelText("Pays de depart");
    fireEvent.change(PaysNameInput, { target: { value: "France" } });
  
    const destinationInput = screen.getByLabelText("Destination :");
    fireEvent.change(destinationInput, { target: { value: "Londres" } });
  
    const dateDepartInput = screen.getByLabelText("Date Depart :");
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
  
    const dateRetourInput = screen.getByLabelText("Date Retour :");
    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });

    const nbPersInput = screen.getByLabelText("Nombre de personnes");
    fireEvent.change(nbPersInput, { target: { value: "2" } });
  
    // Soumettre le formulaire
    const submitButton = screen.getByRole('button', { name: /soumettre/i });
    fireEvent.click(submitButton);
  
    // Vérifier que la fonction handleSubmit est appelée avec les bonnes données
    expect(handleSubmit).toHaveBeenCalledWith({
        paysDepart: "France",
      destination: "Londres",
      dateDepart: "2023-10-01",
      dateRetour: "2023-10-10", 
      nbPersonnes:'2',
    });
  });
});