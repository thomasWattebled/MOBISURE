import AssistanceFormView from '../../components/assistance/AssistanceFormView';
import React from "react";
import { render, screen, fireEvent,waitFor  } from "@testing-library/react";
import '@testing-library/jest-dom';

jest.mock('react-router-dom')
describe("AssuranceVehiculeForm Component", () => {
    const defaultFormData = {
        id_client : '',
        status : 'ATTENTE',
        date : new Date().toISOString(),
        message : '',
        type : '',
        nom : '',
        prenom : '',
        mail : '',
        mdp : '',
        telephone : '',
        ville : '',
        rue : '',
        nbBlesse: null,
        montant : '',
        motif : ''
    };
  
    const mockHandleSubmit = jest.fn();
    const mockHandleChange = jest.fn();
    const mockHandleModalClose = jest.fn();

    const renderForm = (props = {}) => {
        const defaultProps = {
          formData: defaultFormData,
          isModalVisible: false,
          handleSubmit: mockHandleSubmit,
          handleChange: mockHandleChange,
          handleModalClose: mockHandleModalClose
        };
    
        return render(<AssistanceFormView {...defaultProps} {...props} />);
      };
    
      beforeEach(() => {
        jest.clearAllMocks();
      });

    it("renders all form fields correctly", () => {
        renderForm();
    
        // Vérifier que le titre du formulaire est affiché
        expect(screen.getByText("Demande d'Assistance")).toBeInTheDocument();
    
        // Vérifier que les champs sont présents
        expect(screen.getByLabelText("Type d'assistance :")).toBeInTheDocument();
        const typeSelect = screen.getByRole('combobox', { name: /Type d'assistance/i });
        expect(typeSelect).toBeInTheDocument();
        expect(screen.getByLabelText(/Message/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Envoyer la demande' })).toBeInTheDocument();
        expect(screen.getByText("En cas d'urgence, merci de contacter le : 03 20 17 59 47")).toBeInTheDocument();
      });

      it('has all required options in type select', () => {
        renderForm();
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(8);
        expect(options[0]).toHaveValue('');
        expect(options[1]).toHaveValue('AUTO');
        expect(options[2]).toHaveValue('MEDICAL');
        expect(options[3]).toHaveValue('CONTRAT');
        expect(options[4]).toHaveValue('ACCIDENT');
        expect(options[5]).toHaveValue('VOYAGE');
        expect(options[6]).toHaveValue('REMBOURSEMENT');
        expect(options[7]).toHaveValue('AUTRE');
      });


      it('shows required fields when submitting empty form', async () => {
        renderForm();
        fireEvent.submit(screen.getByRole('button', { name: 'Envoyer la demande' }));
        
        await waitFor(() => {
          const typeSelect = screen.getByRole('combobox', { name: /Type d'assistance/i });
          expect(typeSelect).toBeInvalid();
        });
        expect(screen.getByLabelText(/Message/i)).toBeInvalid();
      });



      it('does not show ville and rue fields initially', () => {
        renderForm();
        expect(screen.queryByLabelText('Ville :')).not.toBeInTheDocument();
        expect(screen.queryByLabelText('Rue :')).not.toBeInTheDocument();
      });
    
      it('shows ville and rue fields when type is AUTO', () => {
        renderForm({
          formData: {
            ...defaultFormData,
            type: 'AUTO'
          }
        });
        expect(screen.getByLabelText('Ville :')).toBeInTheDocument();
        expect(screen.getByLabelText('Rue :')).toBeInTheDocument();
      });


      it('shows ville, rue and nbBlesse fields when type is ACCIDENT', () => {
        renderForm({
          formData: {
            ...defaultFormData,
            type: 'ACCIDENT'
          }
        });
        expect(screen.getByLabelText('Ville :')).toBeInTheDocument();
        expect(screen.getByLabelText('Rue :')).toBeInTheDocument();
        expect(screen.getByLabelText('Nombre de bléssés :')).toBeInTheDocument();
      });

      it('shows motif field when type is MEDICAL', () => {
        renderForm({
          formData: {
            ...defaultFormData,
            type: 'MEDICAL'
          }});
        expect(screen.getByLabelText('Entrer le motif de votre demande :')).toBeInTheDocument();
      });
    
      it('calls handleChange when typing in message field', () => {
        renderForm();
        const messageInput = screen.getByLabelText('Message :');
        fireEvent.change(messageInput, { target: { value: 'Test message' } });
        expect(mockHandleChange).toHaveBeenCalled();
      });

      it('calls handleSubmit when form is submitted with valid data', () => {
        renderForm({
          formData: {
            ...defaultFormData,
            type: 'AUTO',
            ville: 'Paris',
            rue: 'Rue de la Tour Eiffel',
            message: 'besoin d aide'
          }
        });  
        const submitButton = screen.getByRole('button', { name: /Envoyer la demande/i });
        fireEvent.click(submitButton);        
        expect(mockHandleSubmit).toHaveBeenCalled();
      });

      it('does not show SuccessDemande modal initially', () => {
        renderForm();
        expect(screen.queryByTestId('success-modal')).not.toBeInTheDocument();
      });
    
      it('shows SuccessDemande modal when isModalVisible is true', () => {
        renderForm({
          isModalVisible: true
        });
        expect(screen.getByText(/succès/i)).toBeInTheDocument();
      });


      it('requires montant and motif when type is REMBOURSEMENT', async () => {
        renderForm({
          formData: {
            ...defaultFormData,
            type: 'REMBOURSEMENT',
            message: 'Test message'
          }
        });
        fireEvent.submit(screen.getByRole('button', { name: 'Envoyer la demande' }));
        await waitFor(() => {
          expect(screen.getByLabelText('Montant du remboursement :')).toBeInvalid();
        });
        expect(screen.getByLabelText('Entrer le motif de votre demande :')).toBeInvalid();

      });

});