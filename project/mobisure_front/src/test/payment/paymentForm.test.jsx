import PaymentForm from '../../components/payment/PaymentForm';
import { validateCardNumber, validateCVV } from '../../components/payment/verificationCarte';
import React from "react";
import { render, screen, fireEvent,waitFor  } from "@testing-library/react";
import '@testing-library/jest-dom';

// Mock des fonctions de validation
jest.mock('../../components/payment/verificationCarte', () => ({
  validateCardNumber: jest.fn(),
  validateCVV: jest.fn(),
}));

describe("PaymentForm Validation", () => {

  it("sets cardNumberError when card number is invalid", () => {
    validateCardNumber.mockReturnValue(false); // Simuler une carte invalide
    render(<PaymentForm />);
    // Ouvrir la popup
    fireEvent.click(screen.getByText("Valider"));
    const cardNumberInput = screen.getByLabelText("Numéro de carte :");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    expect(screen.getByText("Numéro invalide")).toBeInTheDocument();
  });

  it("clears cardNumberError when card number becomes valid", () => {
    validateCardNumber.mockReturnValueOnce(false).mockReturnValueOnce(true); // Simuler une transition invalide -> valide
    render(<PaymentForm />);
    // Ouvrir la popup
    fireEvent.click(screen.getByText("Valider"));
    const cardNumberInput = screen.getByLabelText("Numéro de carte :");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111111" } });
    expect(screen.queryByText("Numéro invalide")).not.toBeInTheDocument();
  });


it("does not submit the form if there are validation errors", () => {
    validateCardNumber.mockReturnValue(false); 
    render(<PaymentForm />);
    fireEvent.click(screen.getByText("Valider"));
    const cardNumberInput = screen.getByLabelText("Numéro de carte :");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    fireEvent.click(screen.getByText("Valider"));
    expect(screen.getByText("Corrigez les erreurs avant de soumettre.")).toBeInTheDocument();
  });

  it("submits the form if all inputs are valid", async () => {
    validateCardNumber.mockReturnValue(true); 
    validateCVV.mockReturnValue(true); 
    global.fetch = jest.fn(() =>
      Promise.resolve({
        text: () => Promise.resolve("success"),
      })
    );

    render(<PaymentForm />);

    // Remplir le formulaire
    fireEvent.change(screen.getByLabelText("Numéro de carte :"), { target: { value: "4111111111111111" } });
    fireEvent.change(screen.getByLabelText("Numéro de carte :"), { target: { value: "John Doe" } });
    fireEvent.change(screen.getByLabelText("CVV :"), { target: { value: "123" } });
    fireEvent.click(screen.getByText("Valider"));

    await waitFor(() => expect(global.fetch).toHaveBeenCalledTimes(1));
  });


  it("updates cardNumber state on input change", () => {
    render(<PaymentForm />);
    const cardNumberInput = screen.getByLabelText("Numéro de carte :");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111111" } });
    expect(cardNumberInput.value).toBe("4111111111111111");
  });




 
  
});