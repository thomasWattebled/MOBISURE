import React from 'react';
import { BrowserRouter, Routes, Route, Link, Outlet } from "react-router-dom";
import '../assets/css/Footer.css';



const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <p>&copy; {new Date().getFullYear()} AssurMob. All rights reserved.</p>
        <ul className="footer-links">
          <li><Link to="/about" className="text-white text-decoration-none">About us</Link></li>
          <li><Link to="/contact" className="text-white text-decoration-none">Contact us</Link></li>
		  <li><Link to="/Privacy" className="text-white text-decoration-none">Privacy Policy</Link></li>
        </ul>
      </div>
    </footer>
  );
};

export default Footer;



