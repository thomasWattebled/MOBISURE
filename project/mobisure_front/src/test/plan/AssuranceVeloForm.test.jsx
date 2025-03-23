import { render, screen } from '@testing-library/react';
import AssuranceVeloForm from '../../components/plans/components/formulaire/AssuranceVeloForm';
import '@testing-library/jest-dom'; // Import jest-dom

describe("AssuranceVeloForm Component", () => {
  it("renders all form fields correctly", () => {
    render(<AssuranceVeloForm />);

    // Vérifier que le titre du formulaire est affiché
    expect(screen.getByText("Formulaire Assurance Vélo")).toBeInTheDocument();

    // Vérifier que les boutons radio sont présents
    expect(screen.getByText("Electrique ?")).toBeInTheDocument();
    expect(screen.getByLabelText("Oui")).toBeInTheDocument();
    expect(screen.getByLabelText("Non")).toBeInTheDocument();

    // Vérifier que le bouton de soumission est présent
    expect(screen.getByRole('button', { name: /soumettre/i })).toBeInTheDocument();
  });
});