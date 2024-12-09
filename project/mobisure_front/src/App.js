import logo from './logo.svg';
import './App.css';
import { AuthProvider } from './components/auth/AuthContext.js';
import Home from './components/pages/Accueil.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { LoginComponent } from './components/auth/LoginComponent.js';
import { LogoutComponent } from './components/auth/LogoutComponent.js';
import { PrivateRoute, WhenUserHasAnyRole, WhenUserIsAuthenticated, WhenUserIsInRole } from "./components/security/PrivateRoute.js";
import { Container, Nav, Navbar } from "react-bootstrap";
import { Outlet, Link } from "react-router-dom";
import RegistrationForm from './components/register/RegistrationForm.js'



export const Layout = (props) => {
	
	return (
	  <>
	    <Navbar bg="light" variant="light" expand="lg">
	      <Container>
	        <Navbar.Toggle aria-controls="basic-navbar-nav" />
	        <Navbar.Collapse id="basic-navbar-nav">
	          <Nav className="me-auto">
			  
			  	<WhenUserIsAuthenticated>
					<Nav.Link as={Link} to={"/home"}>home</Nav.Link>
					<Nav.Link as={Link} to={"/deconnexion"}>déconnexion</Nav.Link>
				</WhenUserIsAuthenticated>    
				   
	          </Nav>
	          <Navbar.Text>
	            Bienvenue sur Mobisure !
	          </Navbar.Text>
	        </Navbar.Collapse>
	      </Container>
	    </Navbar>

	    <Outlet />
	  </>
	)};


export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
		  <Route path="/" element={<Layout />}>
	          <Route index element={<LoginComponent />} />
	          <Route path="/home" element={<Home />} />
			  <Route path="/deconnexion" element={<LogoutComponent />} />
			  <Route path="/register" element={<RegistrationForm />} />
			</Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

