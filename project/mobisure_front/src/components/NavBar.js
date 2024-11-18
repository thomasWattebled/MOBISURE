import { Outlet, Link } from "react-router-dom";
import { Container, Nav, Navbar } from "react-bootstrap";


function NavBar() {
return (
<>
<Navbar bg="light" variant="light" expand="lg">
<Container>
<Navbar.Toggle aria-controls="basic-navbar-nav" />
<Navbar.Collapse id="basic-navbar-nav">

<Nav className="me-auto">

<Nav.Link as={Link} to="/login">Login</Nav.Link>
<Nav.Link as={Link} to="/Logout">Logout</Nav.Link>
</Nav>
</Navbar.Collapse>
</Container>
</Navbar>
<Outlet />
</>
) };

export default NavBar;