import { render, screen,fireEvent, } from '@testing-library/react';
import AssuranceVeloForm from '../../components/plans/components/formulaire/AssuranceVeloForm';
import '@testing-library/jest-dom'; // Import jest-dom
import { BrowserRouter as Router } from "react-router-dom";



describe("AssuranceVeloForm Component", () => {
  it("handles motorisation change correctly", () => {
    const userData = {};
    const setUserData = jest.fn();
    const isModalVisible = false;
    const setModalVisible = jest.fn();
    render(
      <Router>
        <AssuranceVeloForm
          userData={userData}
          setUserData={setUserData}
          isModalVisible={isModalVisible}
          setModalVisible={setModalVisible}
        />
      </Router>
    );

    // Sélectionner l'option "Oui" (Électrique)
    const ouiRadio = screen.getByLabelText("Oui");
    fireEvent.click(ouiRadio);
    expect(ouiRadio.checked).toBe(true);

    // Sélectionner l'option "Non" (Thermique)
    const nonRadio = screen.getByLabelText("Non");
    fireEvent.click(nonRadio);
    expect(nonRadio.checked).toBe(true);
    expect(ouiRadio.checked).toBe(false);
  });

  it("submits the form and navigates to /devis with correct data", () => {
    const userData = {};
    const setUserData = jest.fn();
    const isModalVisible = false;
    const setModalVisible = jest.fn();
    render(
      <Router>
        <AssuranceVeloForm
          userData={userData}
          setUserData={setUserData}
          isModalVisible={isModalVisible}
          setModalVisible={setModalVisible}
        />
      </Router>
    );

    // Sélectionner l'option "Oui" (Électrique)
    const ouiRadio = screen.getByLabelText("Oui");
    fireEvent.click(ouiRadio);

    // Sélectionner une option supplémentaire
    const vandalismeCheckbox = screen.getByLabelText(
      "Protection contre le vandalisme"
    );
    fireEvent.click(vandalismeCheckbox);

    // Soumettre le formulaire
    const submitButton = screen.getByText("Soumettre");
    fireEvent.click(submitButton);

  });
});