
import { AuthProvider } from './components/auth/AuthContext.js';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { LoginComponent } from './components/auth/LoginComponent.js';
import LogoutComponent from './components/auth/LogoutComponent';
import { PrivateRoute, WhenUserHasAnyRole, WhenUserIsAuthenticated, WhenUserIsInRole, WhenUserIsNotAuthenticated } from "./components/security/PrivateRoute.js";
import { Container, Nav, Navbar } from "react-bootstrap";
import { Outlet, Link } from "react-router-dom";
import RegistrationForm from './components/register/RegistrationForm.js'
import Accueil from './pages/Accueil.js'
import PlansSection from './pages/PlansSection.js';

import Footer from './components/Footer.jsx';


export const Layout = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <nav className="navbar">
        <Nav className="navbar-links">
          <WhenUserIsNotAuthenticated>
            <li><Nav.Link as={Link} to="/home">Home</Nav.Link></li>
            <li><Nav.Link as={Link} to="/register">SignUp</Nav.Link></li>
            <li><Nav.Link as={Link} to="/login">Login</Nav.Link></li>
          </WhenUserIsNotAuthenticated>
          <WhenUserIsAuthenticated>
            <li><Nav.Link as={Link} to="/home">Home</Nav.Link></li>
            <li><Nav.Link as={Link} to="/plans">Plans</Nav.Link></li>
            <li><Nav.Link as={Link} to="/deconnexion">Déconnexion</Nav.Link></li>
          </WhenUserIsAuthenticated>
        </Nav>
      </nav>

      <main className="flex-grow-1">
        <Outlet />
      </main>

      <Footer />
    </div>
  );
};



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
			</Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

