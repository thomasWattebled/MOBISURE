import React, { useState } from 'react';
import './HeroSection.css';
import travelImage1 from './image/image.png'; // Importation de la première image
import travelImage2 from './image/image1.png'; // Importation de la deuxième image

const HeroSection = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const images = [travelImage1, travelImage2];

  const moveCarousel = (direction) => {
    const newIndex = (currentIndex + direction + images.length) % images.length;
    setCurrentIndex(newIndex);
  };

  return (
    <div className="hero-section">
      <h1>Discover Affordable Travel Insurance - MOBISURE</h1>
      <p>Choose the best travel plans and enjoy stress-free journeys. Refunds on eligible plans with multi-contract discounts!</p>
      <div className="carousel">
        <div className="carousel-images">
          {images.map((image, index) => (
            <img
              key={index}
              src={image}
              alt={index === 0 ? "Traveling documents" : "Travel essentials"}
              style={{ 
                display: index === currentIndex ? 'block' : 'none', 
                width: '100%', 
                height: 'auto', 
                objectFit: 'cover', 
                maxHeight: '500px' // Ajuste la hauteur maximale si nécessaire
              }}
            />
          ))}
        </div>
        <button className="carousel-button prev" onClick={() => moveCarousel(-1)}>&#10094;</button>
        <button className="carousel-button next" onClick={() => moveCarousel(1)}>&#10095;</button>
      </div>
    </div>
  );
};

export default HeroSection;
