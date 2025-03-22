import React from "react";
import { render, screen, fireEvent,act } from "@testing-library/react";
import PopupConfirmation from "../components/sinistre/popUpConfirmation";
import '@testing-library/jest-dom';


// Mock de GeneratePDF pour éviter de tester son comportement ici
jest.mock("../components/pdf/generatePDF", () => () => <div>GeneratePDF Mock</div>);

describe("ConfirmationModal", () => {
  const onCloseMock = jest.fn();
  const formData = { type: "Accident", date: "2023-10-01", description: "Test description" };
  const pdfConfig = [
    { label: "Type de sinistre", key: "type" },
    { label: "Date du sinistre", key: "date" },
    { label: "Description", key: "description" },
  ];

  it("affiche le message de confirmation et le bouton Générer PDF", () => {
    render(
      <PopupConfirmation
        onClose={onCloseMock}
        formData={formData}
        pdfConfig={pdfConfig}
      />
    );
    expect(screen.getByText("Le sinistre a bien été créé.")).toBeInTheDocument();
    expect(screen.getByText("GeneratePDF Mock")).toBeInTheDocument();
  });

  it("appelle onClose lorsque le bouton Fermer est cliqué", () => {
    render(
      <PopupConfirmation
        onClose={onCloseMock}
        formData={formData}
        pdfConfig={pdfConfig}
      />
    );

    const closeButton = screen.getByText("Fermer");
    fireEvent.click(closeButton);
    expect(onCloseMock).toHaveBeenCalledTimes(1);
  });
});