import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginComponent from './auth/LoginComponent';
import LogoutComponent from './auth/LogoutComponent';
import SignUpComponent from './auth/SignUpComponent.jsx';  // Nouveau composant d'inscription
import Home from './components/Home';

import Layout from './Layout';

function App() {
  return (

      <Router>
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<LoginComponent />} />
          <Route path="/login" element={<LoginComponent />} /> 
          <Route path="/logout" element={<LogoutComponent />} />
          <Route path="/signup" element={<SignUpComponent />} /> {/* Nouvelle route d'inscription */}
          
          {/* Protected Routes */}
          <Route
            element={
                <Layout /> 
            }
          >
            <Route path="/home" element={<Home />} />
          </Route>
        </Routes>
      </Router>

  );
}

export default App;
