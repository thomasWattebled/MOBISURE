import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import AddSinistre from '../../components/sinistre/addSinistre'
// Mock the useAuth hook

jest.mock('../../components/auth/AuthContext', () => ({
  useAuth: () => ({
    getUser: () => ({
      nom: "Jean",
      prenom: "Dupont",
      email: "jean.dupont@example.com",
    }),
  }), 
}));

// Mock the PopupConfirmation component
jest.mock('../../components/sinistre/PopupConfirmation', () => (props) => (
  <div data-testid="popup-confirmation">PopupConfirmation Mock</div>
));

describe("AddSinistre Component", () => {
  it("renders the 'Déclarer un sinistre' button", () => {
    render(<AddSinistre />);
    expect(screen.getByText("+")).toBeInTheDocument();
  });


  it("opens the modal when the button is clicked", () => {
    render(<AddSinistre />);
    fireEvent.click(screen.getByText("+"));
    expect(screen.getByText("Déclarer un sinistre")).toBeInTheDocument();
  });


  it("renders the form fields for 'Véhicule' category", async () => {
    render(<AddSinistre />);
    fireEvent.click(screen.getByText("+"));

    // Select 'Véhicule' category
    fireEvent.change(screen.getByLabelText("Catégorie de sinistre"), {
      target: { value: "Véhicule" },
    });

    // Check for Véhicule-specific fields
    expect(screen.getByLabelText("Immatriculation")).toBeInTheDocument();
    expect(screen.getByLabelText("Marque")).toBeInTheDocument();
    expect(screen.getByLabelText("Modele")).toBeInTheDocument();
    expect(screen.getByLabelText("Constat (photo/PDF)")).toBeInTheDocument();
  });

  it("renders the form fields for 'Santé' category", async () => {
    render(<AddSinistre />);
    fireEvent.click(screen.getByText("+"));

    // Select 'Santé' category
    fireEvent.change(screen.getByLabelText("Catégorie de sinistre"), {
      target: { value: "Santé" },
    });

    // Check for Santé-specific fields
    expect(screen.getByLabelText("Nature de la blessure")).toBeInTheDocument();
    expect(screen.getByLabelText("Hôpital")).toBeInTheDocument();
    expect(screen.getByLabelText("Ordonnance (photo/PDF)")).toBeInTheDocument();
  });

 
 
  
});