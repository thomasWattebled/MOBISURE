import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import GeneratePDF from "../components/pdf/generatePDF";
import jsPDF from "jspdf";
import { AuthProvider } from "../components/auth/AuthContext";
// Mock de jsPDF
jest.mock("jspdf", () => {
  return jest.fn().mockImplementation(() => ({
    text: jest.fn(),
    addImage: jest.fn(),
    save: jest.fn(),
  }));
});

describe("GeneratePDF", () => {
  const data = { type: "Accident", date: "2023-10-01", description: "Test description" };
  const config = [
    { label: "Type de sinistre", key: "type" },
    { label: "Date du sinistre", key: "date" },
    { label: "Description", key: "description" },
  ];

  // Données simulées pour le contexte utilisateur
  const userInfo = {
    nom: "Jean",
    prenom: "Dupont",
    mail: "jean.dupont@example.com",
  };

  it("affiche le bouton Générer PDF", () => {
    render(
      <AuthProvider.Provider value={userInfo}>
        <GeneratePDF data={data} config={config} fileName="test.pdf" />
      </AuthProvider.Provider>
    );

    expect(screen.getByText("Générer PDF")).toBeInTheDocument();
  });

  it("génère un PDF avec les informations de l'utilisateur et du formulaire", () => {
    render(
      <AuthProvider.Provider value={userInfo}>
        <GeneratePDF data={data} config={config} fileName="test.pdf" />
      </AuthProvider.Provider>
    );

    const generateButton = screen.getByText("Générer PDF");
    fireEvent.click(generateButton);

    // Vérifie que les méthodes de jsPDF sont appelées
    expect(jsPDF).toHaveBeenCalledTimes(1);
    const pdfInstance = jsPDF.mock.results[0].value;
    expect(pdfInstance.text).toHaveBeenCalledWith("Nom: Dupont", 10, 10);
    expect(pdfInstance.text).toHaveBeenCalledWith("Prénom: Jean", 10, 20);
    expect(pdfInstance.text).toHaveBeenCalledWith("Email: jean.dupont@example.com", 10, 30);
    expect(pdfInstance.text).toHaveBeenCalledWith("Type de sinistre: Accident", 10, 50);
    expect(pdfInstance.text).toHaveBeenCalledWith("Date du sinistre: 2023-10-01", 10, 60);
    expect(pdfInstance.text).toHaveBeenCalledWith("Description: Test description", 10, 70);
    expect(pdfInstance.save).toHaveBeenCalledWith("test.pdf");
  });
});