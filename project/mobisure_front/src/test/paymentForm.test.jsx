import PaymentForm from '../components/payment/PaymentForm';
import { validateCardNumber, validateCVV } from '../components/payment/verificationCarte';
import React from "react";
import { render, screen, fireEvent,waitFor  } from "@testing-library/react";
import '@testing-library/jest-dom';

// Mock des fonctions de validation
jest.mock('../components/payment/verificationCarte', () => ({
  validateCardNumber: jest.fn(),
  validateCVV: jest.fn(),
}));

describe("PaymentForm Validation", () => {

  it("sets cardNumberError when card number is invalid", () => {
    validateCardNumber.mockReturnValue(false); // Simuler une carte invalide
    render(<PaymentForm />);
    // Ouvrir la popup
    fireEvent.click(screen.getByText("Passer au reglement"));
    const cardNumberInput = screen.getByLabelText("Card Number:");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    expect(screen.getByText("Invalid card number")).toBeInTheDocument();
  });

  it("clears cardNumberError when card number becomes valid", () => {
    validateCardNumber.mockReturnValueOnce(false).mockReturnValueOnce(true); // Simuler une transition invalide -> valide
    render(<PaymentForm />);
    // Ouvrir la popup
    fireEvent.click(screen.getByText("Passer au reglement"));
    const cardNumberInput = screen.getByLabelText("Card Number:");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111111" } });
    expect(screen.queryByText("Invalid card number")).not.toBeInTheDocument();
  });


it("does not submit the form if there are validation errors", () => {
    validateCardNumber.mockReturnValue(false); 
    render(<PaymentForm />);
    fireEvent.click(screen.getByText("Passer au reglement"));
    const cardNumberInput = screen.getByLabelText("Card Number:");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111112" } });
    fireEvent.click(screen.getByText("Submit"));
    expect(screen.getByText("Please fix the errors before submitting.")).toBeInTheDocument();
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

    // Ouvrir la popup
    fireEvent.click(screen.getByText("Passer au reglement"));

    // Remplir le formulaire
    fireEvent.change(screen.getByLabelText("Card Number:"), { target: { value: "4111111111111111" } });
    fireEvent.change(screen.getByLabelText("Card Holder:"), { target: { value: "John Doe" } });
    fireEvent.change(screen.getByLabelText("CVV:"), { target: { value: "123" } });
    fireEvent.click(screen.getByText("Submit"));

    await waitFor(() => expect(global.fetch).toHaveBeenCalledTimes(1));
    expect(await screen.findByText("success")).toBeInTheDocument();
  });


  it("updates cardNumber state on input change", () => {
    render(<PaymentForm />);
    fireEvent.click(screen.getByText("Passer au reglement"));
    const cardNumberInput = screen.getByLabelText("Card Number:");
    fireEvent.change(cardNumberInput, { target: { value: "4111111111111111" } });
    expect(cardNumberInput.value).toBe("4111111111111111");
  });


  it("toggles isOpen state when the button is clicked", () => {
    render(<PaymentForm />);
    fireEvent.click(screen.getByText("Passer au reglement"));
    expect(screen.getByText("Simulate Payment")).toBeInTheDocument();
    fireEvent.click(screen.getByText("Close"));
    expect(screen.queryByText("Simulate Payment")).not.toBeInTheDocument();
  });

 
  
});