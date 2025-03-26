import React from 'react';
import { render, screen, fireEvent, waitFor, act } from '@testing-library/react';
import { useNavigate } from 'react-router-dom';
import AssistanceList from '../../components/assistance/AssistanceList';
import UserService from '../../services/userService';
import '@testing-library/jest-dom';

jest.mock('react-router-dom', () => ({
  useNavigate: jest.fn(),
}));

jest.mock('../../services/userService', () => ({
  fetchUserByEmail: jest.fn(),
}));

const mockUseAuth = {
    getUser: jest.fn(),
  };
  
  jest.mock('../../components/auth/AuthContext', () => ({
    __esModule: true,
    useAuth: () => mockUseAuth,
  }));

global.fetch = jest.fn();

describe('AssistanceList', () => {
  const mockNavigate = jest.fn();
  const mockUser = { id: 1, username: 'admin@test.com' };
  const mockAssistanceData = [
    {
      id: 1,
      numDossier: 'DOSS-001',
      nom: 'Dupont',
      prenom: 'Jean',
      type: 'AUTO',
      status: 'ATTENTE',
      date: '2023-05-01T00:00:00.000Z',
      message: 'Problème avec ma voiture',
      id_client: 10,
    },
  ];






  beforeEach(() => {
    useNavigate.mockReturnValue(mockNavigate);
    // Correction: Configurer le mock useAuth correctement
    mockUseAuth.getUser.mockReturnValue({ username: 'admin@test.com' });
    UserService.fetchUserByEmail.mockResolvedValue(mockUser);
    fetch.mockImplementation((url) => {
      if (url.includes('assistance/disponnible')) {
        return Promise.resolve({
          ok: true,
          json: () => Promise.resolve(mockAssistanceData),
        });
      }
      if (url.includes('updateStatus')) {
        return Promise.resolve({ ok: true });
      }
      if (url.includes('addAccess')) {
        return Promise.resolve({ ok: true });
      }
      return Promise.reject(new Error('Not found'));
    });
  });



  afterEach(() => {
    jest.clearAllMocks();
  });

  it('affiche le chargement initial', async () => {
    fetch.mockImplementationOnce(() => new Promise(() => {}));
    render(<AssistanceList />);
    expect(screen.getByText("Liste des Demandes d'Assistance")).toBeInTheDocument();
  });


  it('affiche les erreurs', async () => {
    fetch.mockRejectedValueOnce(new Error('Erreur de récupération'));
    render(<AssistanceList />);
    await waitFor(() => {
      expect(screen.getByText(/Erreur : Erreur de récupération/i)).toBeInTheDocument();
    });
  });


  it('affiche correctement les données après chargement', async () => {
    render(<AssistanceList />);
    await waitFor(() => {
        expect(screen.getByText('Numéro dossier')).toBeInTheDocument();
      });
        expect(await screen.findByText('Dupont')).toBeInTheDocument();
        expect(screen.getByText('Jean')).toBeInTheDocument();
        expect(screen.getByText('AUTO')).toBeInTheDocument();
        expect(screen.getByText('01/05/2023')).toBeInTheDocument();
  });


  it('devrait gérer les erreurs de fetch', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    render(<AssistanceList />);
    await waitFor(() => {
      expect(screen.getByText(/Erreur : API Error/i)).toBeInTheDocument();
    });
  });

  it('devrait mettre à jour le statut', async () => {
    render(<AssistanceList />);
    
    await waitFor(async () => {
      const select = screen.getAllByRole('combobox')[0];
      await act(async () => {
        fireEvent.change(select, { target: { value: 'TRAITEMENT' } });
      });
      
      expect(fetch).toHaveBeenCalledWith(
        'http://localhost:8081/assistance/updateStatus/1',
        expect.objectContaining({
          method: 'PUT',
          body: JSON.stringify({ status: 'TRAITEMENT' }),
        })
      );
    });
  });


  it('devrait gérer l\'assistance', async () => {
    render(<AssistanceList />);
    
    await waitFor(() => {
      fireEvent.click(screen.getByText('Gérer'));
      expect(fetch).toHaveBeenCalledWith(
        'http://localhost:8081/access/addAccess?idAssistance=DOSS-001&idUser=1',
        expect.objectContaining({
          method: 'POST',
        })
      );
    });
  });


});