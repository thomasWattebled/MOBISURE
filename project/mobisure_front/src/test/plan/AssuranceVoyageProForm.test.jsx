import { render, screen,fireEvent } from '@testing-library/react';
import VoyageProfessionnelForm from '../../components/plans/components/formulaire/VoyageProForm';
import userEvent from '@testing-library/user-event';
import '@testing-library/jest-dom'; // Import jest-dom

describe("VoyageProfessionnelForm Component", () => {
  it("renders all form fields correctly", () => {
    render(
      <VoyageProfessionnelForm /> 
    );
    // Vérifier que les champs sont présents
		expect(screen.getByLabelText(/Nom de l'entreprise :/i)).toBeInTheDocument();
		expect(screen.getByLabelText(/Départ/i)).toBeInTheDocument();
	    expect(screen.getByLabelText(/Arrivée/i)).toBeInTheDocument();
	    expect(screen.getByLabelText(/Date Depart/i)).toBeInTheDocument();
	    expect(screen.getByLabelText(/Date Retour/i)).toBeInTheDocument();
	    expect(screen.getByRole("button", { name: /soumettre/i })).toBeInTheDocument();
  });

  
    it("updates form data when input fields are changed", async () => {
    render(
      <VoyageProfessionnelForm />
    );
    const companyNameInput = screen.getByLabelText(/Nom de l'entreprise :/i);
    fireEvent.change(companyNameInput, { target: { value: "Ma Société" } });
    expect(companyNameInput).toHaveValue("Ma Société");

	const dateDepartInput = screen.getByLabelText(/Date Depart/i);
	    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
	    expect(dateDepartInput).toHaveValue("2023-10-01");

	    const dateRetourInput = screen.getByLabelText(/Date Retour/i);
	    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });
	    expect(dateRetourInput).toHaveValue("2023-10-10");

  });
   
  it("submits the form with correct data", () => {
    
    render(<VoyageProfessionnelForm />);

    // Remplir le formulaire
    userEvent.type(screen.getByLabelText(/Nom de l'entreprise :/i), "Ma Société");
    userEvent.type(screen.getByLabelText(/Départ/i), "France");
    userEvent.type(screen.getByLabelText(/Arrivée/i), "Angleterre");
    userEvent.type(screen.getByLabelText(/Date Depart/i), "2023-10-01");
    userEvent.type(screen.getByLabelText(/Date Retour/i), "2023-10-10");

    // Soumettre le formulaire
    const submitButton = screen.getByRole("button", { name: /soumettre/i });
    userEvent.click(submitButton); // Simuler un clic sur le bouton de soumission
  });

});