import { Outlet, Link } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';


function Navigationbar() {
 

  return (
    <Navbar bg="primary" variant="dark" className="px-3">
      <Container>
        <Navbar.Brand className="text-light" style={{ cursor: 'default' }}>
          Mobisure
        </Navbar.Brand>
        <Nav className="ml-auto">
              <Nav.Link as={Link} to="/home">Home</Nav.Link>
              <Nav.Link as={Link} to="/logout">Logout</Nav.Link>
        </Nav>

      </Container>
    </Navbar>
  );
}

function Layout() {
  return (
    <div>
      <Navigationbar />
      <div className="content">
        <Outlet /> {/* Routes will be rendered here */}
      </div>
    </div>
  );
}

export default Layout;