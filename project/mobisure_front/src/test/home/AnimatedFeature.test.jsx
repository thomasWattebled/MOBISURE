import { render, screen } from '@testing-library/react';
import AnimatedFeature from '../../components/home/AnimatedFeature';
import '@testing-library/jest-dom'; // Import jest-dom
import Avion from '../../assets/gif/avion.gif';
import Velo from '../../assets/gif/velo.gif';
import Valise from '../../assets/gif/valise.gif';
import Voiture from '../../assets/gif/voiture.gif';
describe("AnimatedFeature Component", () => {
    const features = [
        { gif: Velo, title: "Voyagez à vélo", description: "Des solutions adaptées pour vos trajets écologiques." },
        { gif: Voiture, title: "Conduite sereine", description: "Une couverture complète pour vos déplacements en voiture." },
        { gif: Avion, title: "Prêt pour l’aventure", description: "Assurez vos biens lors de vos escapades." },
        { gif: Valise, title: "Bagages protégés", description: "Une assistance garantie pour vos bagages perdus ou endommagés." },
      ];

  it("displays all features correctly", () => {
    render(<AnimatedFeature />);

    // Vérifier que chaque fonctionnalité est affichée
    features.forEach((feature) => {
      // Vérifier que le GIF est affiché
      const gifElement = screen.getByAltText(feature.title);
      expect(gifElement).toBeInTheDocument();
      expect(gifElement).toHaveAttribute('src', feature.gif);

      // Vérifier que le titre est affiché
      expect(screen.getByText(feature.title)).toBeInTheDocument();

      // Vérifier que la description est affichée
      expect(screen.getByText(feature.description)).toBeInTheDocument();
    }); 
  });

  it("has correct alt attributes for images", () => {
    render(<AnimatedFeature />);
  
    // Vérifier que chaque image a un attribut `alt` correct
    features.forEach((feature) => {
      const gifElement = screen.getByAltText(feature.title);
      expect(gifElement).toHaveAttribute('alt', feature.title);
    });
  });
});