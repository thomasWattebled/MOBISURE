import logo from './logo.svg';
import './App.css';
import { AuthProvider } from './components/auth/AuthContext.js';
import Home from './components/pages/Accueil.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { LoginComponent } from './components/auth/LoginComponent.js';
import { LogoutComponent } from './components/auth/LogoutComponent.js';
import { PrivateRoute, WhenUserHasAnyRole, WhenUserIsAuthenticated, WhenUserIsInRole, WhenUserIsNotAuthenticated } from "./components/security/PrivateRoute.js";
import { Container, Nav, Navbar } from "react-bootstrap";
import { Outlet, Link } from "react-router-dom";
import RegistrationForm from './components/register/RegistrationForm.js'
import Accueil from './components/pages/Accueil.js';
import PlansSection from './components/pages/PlansSection.js';


export const Layout = (props) => {
	
	return (
	  <>
	    <nav className="navbar">
	          <Nav className="navbar-links">
			  
			  	<WhenUserIsNotAuthenticated>
					<li><Nav.Link as={Link} to={"/home"}>home</Nav.Link></li>
					<li><Nav.Link as={Link} to={"/register"}>SignUp</Nav.Link></li>
					<li><Nav.Link as={Link} to={"/login"}>Login</Nav.Link></li>
					<li><Nav.Link as={Link} to={"/plans"}>plans</Nav.Link></li>
				</WhenUserIsNotAuthenticated>
			  
			  	<WhenUserIsAuthenticated>
					<li><Nav.Link as={Link} to={"/home"}>home</Nav.Link></li>
					<li><Nav.Link as={Link} to={"/plans"}>plans</Nav.Link></li>
					<li><Nav.Link as={Link} to={"/deconnexion"}>déconnexion</Nav.Link></li>
				</WhenUserIsAuthenticated>    
				   
	          </Nav>
	    </nav>
	    <Outlet />
	  </>
	)};


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

