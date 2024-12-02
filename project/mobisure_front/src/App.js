import logo from './logo.svg';
import './App.css';
import { AuthProvider } from './components/auth/AuthContext.js';
import Home from './components/pages/Accueil.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { LoginComponent } from './components/auth/LoginComponent.js';

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

