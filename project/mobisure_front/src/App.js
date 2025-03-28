import React from 'react';
import { BrowserRouter, Routes, Route, Link, Outlet } from "react-router-dom";
import { Nav, NavDropdown } from "react-bootstrap"; // Import de NavDropdown
import { AuthProvider } from './components/auth/AuthContext.js';
import { WhenUserHasAnyRole, WhenUserIsAuthenticated, WhenUserIsNotAuthenticated } from "./components/security/PrivateRoute.js";
import LoginComponent from './components/auth/LoginComponent.js';
import LogoutComponent from './components/auth/LogoutComponent.js';
import RegistrationForm from './components/register/RegistrationForm.js';
import Accueil from './pages/Accueil.jsx';
import PlansSection from './pages/PlansSection.jsx';
import AboutUs from './pages/AboutUs.jsx';
import Contact from './pages/Contact.jsx';
import PrivacyPolicy from './pages/PrivacyPolicy.jsx';
import Footer from './components/Footer.jsx';
import travelImage7 from './assets/image/logo.png';
import Emission from './pages/Emission.jsx';
import PageUser from './components/admin/pageUser';
import { WhenUserIsInRole } from './components/security/PrivateRoute.js';
import MyInformation from './components/user/MyInformation.js';
import MdpForm from './components/auth/MdpForm.js';
import FormUpdateClient from './components/admin/formUpdateClient.js';
import MessageApp from './components/messagerie/MessageApp.js';
import AssistanceList from './components/assistance/AssistanceList.js';
import MyAssistance from './components/assistance/MyAssistance.js';
import Payment from './pages/Payment.jsx';
import MyFolder from './components/assistance/MyFolder.js';
import MyContrat from './components/contrat/MyContrat.js';
import Sinistre from './pages/Sinistre.jsx';
import RacapPlan from './pages/RecapPlan.jsx';
import './assets/css/App.css';
import VoyageForm from './components/devis/VoyageForm.jsx';
import ContratList from './components/contrat/ContratList.js';

export const Layout = () => (
  <>
  <div className="d-flex flex-column min-vh-100">
    <nav className="navbar d-flex align-items-center px-3" style={{ backgroundColor: '#00aaff' }}>
      <img src={travelImage7} alt="MOBISURE Logo" style={{ height: '40px', marginRight: '15px' }} />
      <div className="navbar-brand text-white fw-bold">MOBISURE</div>
      <Nav className="navbar-links ms-auto d-flex gap-3">
        <WhenUserIsNotAuthenticated>
          <Link to="/home" className="text-white text-decoration-none">Home</Link>
          <Link to="/register" className="text-white text-decoration-none">SignUp</Link>
          <Link to="/login" className="text-white text-decoration-none">Login</Link>
          <Link to="/devisvoyage" className="text-white text-decoration-none">Faire un devis pour voyager</Link>
        </WhenUserIsNotAuthenticated>
        <WhenUserIsAuthenticated>
          <Link to="/home" className="text-white text-decoration-none">Home</Link>
          <Link to="/emission" className="text-white text-decoration-none">Emission</Link>
          <Link to="/plans" className="text-white text-decoration-none">Plans</Link>

          {/* Menu déroulant pour les informations de l'utilisateur */}
          <NavDropdown title="Mes informations" id="user-dropdown" className="text-white">
            <NavDropdown.Item as={Link} to="/userInformation">Mes informations</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/mesContrats">Mes contrats</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/mesSinistres">Mes sinistres</NavDropdown.Item>
			<Link to="/myassistance" className="text-white text-decoration-none">Mes assistances</Link>
          </NavDropdown>

          {/* Menu déroulant pour les rôles administrateur et conseiller */}
          <WhenUserIsInRole role="ADMIN">
            <NavDropdown title="Gestion" id="admin-dropdown" className="text-white">
              <NavDropdown.Item as={Link} to="/pageUser">Gestion des utilisateurs</NavDropdown.Item>
            </NavDropdown>
          </WhenUserIsInRole>

          
		  <WhenUserHasAnyRole roles={["MEDECIN","PARTENAIRE","CONSEILLER"]}>
            <NavDropdown title="Gestion" id="conseiller-dropdown" className="text-red">
			 <WhenUserIsInRole role="CONSEILLER">
              <NavDropdown.Item as={Link} to="/pageUser">Gestion des clients</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/assistance/Liste">Les demandes d'assistance</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/contrats">Les contrats</NavDropdown.Item>
			 </WhenUserIsInRole>
			  	<NavDropdown.Item as={Link} to="/assistance/mesDossier">Mes dossiers</NavDropdown.Item>
            </NavDropdown>
		 </WhenUserHasAnyRole>
		  

          {/* Autres liens */}
          <Link to="/messagerie" className="text-white text-decoration-none">Messagerie</Link>
          <Link to="/deconnexion" className="text-white text-decoration-none">Déconnexion</Link>
        </WhenUserIsAuthenticated>
      </Nav>
    </nav>
    <main className="flex-grow-1">
      <Outlet />
    </main>
    <Footer />
  </div>
  </>
);

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Accueil />} />
            <Route path="/home" element={<Accueil />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/deconnexion" element={<LogoutComponent />} />
            <Route path="/register" element={<RegistrationForm />} />
            <Route path="/plans" element={<PlansSection />} />
            <Route path="/about" element={<AboutUs />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/privacy" element={<PrivacyPolicy />} />
            <Route path="/emission" element={<Emission />}/>
            <Route path="/pageUser" element={<PageUser />} />
            <Route path="/userInformation" element={<MyInformation />} />
            <Route path="/changeMdp" element={<MdpForm />} />
            <Route path="/updateClient/:id" element={<FormUpdateClient />} />
            <Route path="/messagerie" element={<MessageApp />} />
            <Route path="/messagerie/:userId" element={<MessageApp />} />
            <Route path="/assistance/Liste" element={<AssistanceList />} />
            <Route path="/myassistance" element={<MyAssistance />} />
            <Route path="/mesSinistres" element={<Sinistre />} />
            <Route path="/assistance/mesDossier" element={<MyFolder />} />
            <Route path="/mesContrats" element={<MyContrat />} />
            <Route path="/mesSinistres" element={<Sinistre />} />
            <Route path="/payment" element={<Payment />} />
            <Route path="/devisvoyage" element={<VoyageForm />} />
			      <Route path="/devis" element={<RacapPlan />} />
            <Route path="/contrats" element={<ContratList />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

