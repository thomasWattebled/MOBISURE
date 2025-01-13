import React from 'react';
import { BrowserRouter, Routes, Route, Link, Outlet } from "react-router-dom";
import { Nav } from "react-bootstrap";
import { AuthProvider } from './components/auth/AuthContext.js';
import { WhenUserIsAuthenticated, WhenUserIsNotAuthenticated } from "./components/security/PrivateRoute.js";
import LoginComponent from './components/auth/LoginComponent.js';
import LogoutComponent from './components/auth/LogoutComponent.js';
import RegistrationForm from './components/register/RegistrationForm.js';
import Accueil from './pages/Accueil.js';
import PlansSection from './pages/PlansSection.js';
import AboutUs from './pages/AboutUs.js';
import Contact from './pages/Contact.js';
import PrivacyPolicy from './pages/PrivacyPolicy.js';
import Footer from './components/Footer.js';
import travelImage7 from './assets/image/logo.png';
import PageUser from './components/admin/pageUser';
import {WhenUserIsInRole} from './components/security/PrivateRoute.js';
import MyInformation from './components/user/MyInformation.js';
import MdpForm from './components/auth/MdpForm.js';

export const Layout = () => (
  <div className="d-flex flex-column min-vh-100">
    <nav className="navbar d-flex align-items-center px-3" style={{ backgroundColor: '#00aaff' }}>
      <img src={travelImage7} alt="MOBISURE Logo" style={{ height: '40px', marginRight: '15px' }} />
      <div className="navbar-brand text-white fw-bold">MOBISURE</div>
      <Nav className="navbar-links ms-auto d-flex gap-3">
        <WhenUserIsNotAuthenticated>
          <Link to="/home" className="text-white text-decoration-none">Home</Link>
          <Link to="/register" className="text-white text-decoration-none">SignUp</Link>
          <Link to="/login" className="text-white text-decoration-none">Login</Link>
        </WhenUserIsNotAuthenticated>
        <WhenUserIsAuthenticated>
          <Link to="/home" className="text-white text-decoration-none">Home</Link>
          <Link to="/plans" className="text-white text-decoration-none">Plans</Link>
		  <Link to="/userInformation" className="text-white text-decoration-none">Mes informations</Link>
		  <WhenUserIsInRole role="ADMIN">
		  	<Link to="/pageUser" className="text-white text-decoration-none">Admin</Link>
		  </WhenUserIsInRole>
          <Link to="/deconnexion" className="text-white text-decoration-none">Déconnexion</Link>
        </WhenUserIsAuthenticated>
      </Nav>
    </nav>
    <main className="flex-grow-1">
      <Outlet />
    </main>
    <Footer />
  </div>
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
			<Route path="/pageUser" element={<PageUser />} />
			<Route path="/userInformation" element={<MyInformation />} />
			<Route path="/changeMdp" element={<MdpForm />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
