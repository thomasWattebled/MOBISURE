import React from 'react';
import Carousel from '../components/home/Carousel';
import FeaturesSection from '../components/home/Feature';
import AnimatedFeature from '../components/home/AnimatedFeature';
import TestimonialSection from '../components/home/Testimonial';
import PricingSection from '../components/home/Pricing';
import '../assets/css/HeroSection.css';
import travelImage1 from '../assets/image/travelImage1.jpg';
import travelImage2 from '../assets/image/travelImage2.png';
import travelImage3 from '../assets/image/travelImage3.jpg';
import travelImage4 from '../assets/image/travelImage4.jpg';
import travelImage5 from '../assets/image/travelImage5.png';
import travelImage6 from '../assets/image/travelImage6.png';
import travelImage7 from '../assets/image/travelImage7.jpg';

const Accueil = () => {
  const images = [travelImage1, travelImage2,travelImage3,travelImage4,travelImage5,travelImage6,travelImage7];

  return (
    <>
      {/* Hero Section */}
      <div className="hero-section">
        <h1>Découvrez une assurance voyage abordable - MOBISURE</h1>
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
