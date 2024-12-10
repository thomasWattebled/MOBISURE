import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './Navbar';
import HeroSection from './components/HeroSection';
import PlansSection from './components/PlansSection';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<HeroSection />} />
        <Route path="/plans" element={<PlansSection />} />
      </Routes>
    </Router>
  );
};

export default App;