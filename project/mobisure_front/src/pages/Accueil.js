import React from 'react';
import Carousel from '../components/home/Carousel';
import FeaturesSection from '../components/home/Feature';
import AnimatedFeature from '../components/home/AnimatedFeature';
import TestimonialSection from '../components/home/Testimonial';
import PricingSection from '../components/home/Pricing';
import '../assets/css/HeroSection.css';
import travelImage1 from '../assets/image/image.png';
import travelImage2 from '../assets/image/image1.png';

const Accueil = () => {
  const images = [travelImage1, travelImage2];

  return (
    <>
      {/* Hero Section */}
      <div className="hero-section">
        <h1>Discover Affordable Travel Insurance - MOBISURE</h1>
        <Carousel images={images} />
      </div>

      {/* Features Section */}
      <FeaturesSection />

      {/* Animated Feature Section */}
      <AnimatedFeature />

      {/* Testimonial Section */}
      <TestimonialSection />

      {/* Pricing Section */}
      <PricingSection />
    </>
  );
};

export default Accueil;
