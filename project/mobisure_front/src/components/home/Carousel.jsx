import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../../assets/css/Carousel.css';

const Carousel = ({ images, visibleCount }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const moveCarousel = (direction) => {
    const maxIndex = images.length - visibleCount;
    const newIndex = Math.min(Math.max(currentIndex + direction, 0), maxIndex);
    setCurrentIndex(newIndex);
  };

  return (
    <div className="carousel">
      {/* Images */}
      <div
        className="carousel-images"
        style={{ transform: `translateX(-${currentIndex * (100 / visibleCount)}%)` }}
      >
        {images.map((image, index) => (
          <div key={index} className="carousel-image-container">
            <img src={image} alt={`Slide ${index + 1}`} className="carousel-image" />
          </div>
        ))}
      </div>

      {/* Boutons de navigation */}
      <button className="carousel-button prev" onClick={() => moveCarousel(-1)}>
        &#10094;
      </button>
      <button className="carousel-button next" onClick={() => moveCarousel(1)}>
        &#10095;
      </button>
    </div>
  );
};

Carousel.propTypes = {
  images: PropTypes.arrayOf(PropTypes.string).isRequired,
  visibleCount: PropTypes.number, // Nombre d'images visibles
};

Carousel.defaultProps = {
  visibleCount: 3, // Par défaut, affiche 3 images
};

export default Carousel;
