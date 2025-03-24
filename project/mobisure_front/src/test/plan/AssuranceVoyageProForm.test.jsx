import { render, screen,fireEvent } from '@testing-library/react';
import VoyageProfessionnelForm from '../../components/plans/components/formulaire/VoyageProForm';
import '@testing-library/jest-dom'; // Import jest-dom

describe("VoyageProfessionnelForm Component", () => {
  it("renders all form fields correctly", () => {
    render(
      <VoyageProfessionnelForm /> 
    );
    // Vérifier que les champs sont présents
    expect(screen.getByLabelText("Nom de l'entreprise :")).toBeInTheDocument();
    expect(screen.getByLabelText("Destination :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Depart :")).toBeInTheDocument();
    expect(screen.getByLabelText("Date Retour :")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument();
  });

  
    it("updates form data when input fields are changed", async () => {
    render(
      <VoyageProfessionnelForm />
    );
    const companyNameInput = screen.getByLabelText("Nom de l'entreprise :");
    fireEvent.change(companyNameInput, { target: { value: "Ma Société" } });
    expect(companyNameInput).toHaveValue("Ma Société");
    const destinationInput = screen.getByLabelText("Destination :");
    fireEvent.change(destinationInput, { target: { value: "Paris" } });
    expect(destinationInput).toHaveValue("Paris");

    const dateDepartInput = screen.getByLabelText("Date Depart :");
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
    expect(dateDepartInput).toHaveValue("2023-10-01");
    
    const dateArriveInput = screen.getByLabelText("Date Retour :");
    fireEvent.change(dateArriveInput, { target: { value: "2023-10-10" } });
    expect(dateArriveInput).toHaveValue("2023-10-10");
  });
   

  it("submits the form with correct data", () => {
    const handleSubmit = jest.fn();
    render(
      <VoyageProfessionnelForm  onSubmit={handleSubmit}
      />
    );
  
    // Remplir le formulaire
    fireEvent.change(screen.getByLabelText("Nom de l'entreprise :"), { target: { value: "Ma Société" } });
    fireEvent.change(screen.getByLabelText("Destination :"), { target: { value: "Paris" } });
    fireEvent.change(screen.getByLabelText("Date Depart :"), { target: { value: "2023-10-01" } });
    fireEvent.change(screen.getByLabelText("Date Retour :"), { target: { value: "2023-10-10" } });
  
    // Soumettre le formulaire
    fireEvent.click(screen.getByRole('button', { name: /soumettre/i }));
  
    // Vérifier que la fonction handleSubmit est appelée avec les bonnes données
    expect(handleSubmit).toHaveBeenCalledWith({
      entreprise: "Ma Société",
      paysArrive: "Paris",
      dateDepart: "2023-10-01",
      dateArrive: "2023-10-10",
      options: []
    });
  });
});