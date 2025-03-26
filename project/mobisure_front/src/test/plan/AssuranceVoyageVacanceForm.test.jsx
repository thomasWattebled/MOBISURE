import { render, screen, fireEvent } from "@testing-library/react";
import VoyageVacanceForm from "../../components/plans/components/formulaire/VoyageVacanceForm";
import userEvent from '@testing-library/user-event';
import "@testing-library/jest-dom"; // Import jest-dom

describe("VoyageVacanceForm Component", () => {
  it("renders all form fields correctly", () => {
    render(<VoyageVacanceForm />);
    
    // Vérifier que le titre du formulaire est affiché
    expect(screen.getByText("Formulaire pour Voyage Vacance")).toBeInTheDocument();
    
    // Vérifier la présence des champs
    expect(screen.getByLabelText(/Départ/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Arrivée/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Date Depart/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Date Retour/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Nombre de personnes/i)).toBeInTheDocument(); // Ajouté
    expect(screen.getByRole("button", { name: /soumettre/i })).toBeInTheDocument();
  });

  it("updates form data when input fields are changed", () => {
    render(<VoyageVacanceForm />);

    const dateDepartInput = screen.getByLabelText(/Date Depart/i);
    fireEvent.change(dateDepartInput, { target: { value: "2023-10-01" } });
    expect(dateDepartInput).toHaveValue("2023-10-01");

    const dateRetourInput = screen.getByLabelText(/Date Retour/i);
    fireEvent.change(dateRetourInput, { target: { value: "2023-10-10" } });
    expect(dateRetourInput).toHaveValue("2023-10-10");

	const nbPersonnesInput = screen.getByLabelText(/Nombre de personnes/i);
	fireEvent.change(nbPersonnesInput, { target: { value: "deux à trois" } });
	expect(nbPersonnesInput).toHaveValue("deux à trois");
  });

  it("submits the form with correct data", async () => {
    render(<VoyageVacanceForm />);

    // Remplir le formulaire
    fireEvent.change(screen.getByLabelText(/Départ/i), { target: { value: "France" } });
    fireEvent.change(screen.getByLabelText(/Arrivée/i), { target: { value: "Angleterre" } });
    fireEvent.change(screen.getByLabelText(/Date Depart/i), { target: { value: "2023-10-01" } });
    fireEvent.change(screen.getByLabelText(/Date Retour/i), { target: { value: "2023-10-10" } });
    fireEvent.change(screen.getByLabelText(/Nombre de personnes/i), { target: { value: "2" } });

    // Cocher une option (ex: "Annulation toutes causes")
    const assuranceCheckbox = screen.getByLabelText(/Annulation toutes causes/i);
    userEvent.click(assuranceCheckbox);

    // Soumettre le formulaire
    const submitButton = screen.getByRole("button", { name: /Soumettre/i });
    userEvent.click(submitButton);

    
  });

});
