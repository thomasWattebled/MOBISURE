import { render, screen, fireEvent } from '@testing-library/react';
import Carousel from '../../components/home/Carousel';
import '@testing-library/jest-dom';
describe("Carousel Component", () => {
  const images = [
    { image: "image1.jpg", description: "Description 1" },
    { image: "image2.jpg", description: "Description 2" },
    { image: "image3.jpg", description: "Description 3" },
    { image: "image4.jpg" }, 
  ];

  it("displays the correct number of images", () => {
    render(<Carousel images={images} visibleCount={3} />);

    // Vérifier que 3 images sont visibles
    const imageContainers = screen.getAllByRole('img');
    expect(imageContainers).toHaveLength(4);
  });

  it("displays image descriptions if they exist", () => {
    render(<Carousel images={images} visibleCount={3} />);

    // Vérifier que les descriptions sont affichées
    expect(screen.getByText("Description 1")).toBeInTheDocument();
    expect(screen.getByText("Description 2")).toBeInTheDocument();
    expect(screen.getByText("Description 3")).toBeInTheDocument();

    // Vérifier qu'aucune description n'est affichée pour l'image sans description
    const image4Description = screen.queryByText("Description 4");
    expect(image4Description).not.toBeInTheDocument();
  });

  
  it("moves to the next set of images when next button is clicked", () => {
    render(<Carousel images={images} visibleCount={3} />);
  
    // Vérifier que les 3 premières images sont visibles
    expect(screen.getByAltText("Slide 1")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 2")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 3")).toBeInTheDocument();
  
    // Cliquer sur le bouton "suivant"
    const nextButton = screen.getByRole('button', { name: /next/i });
    fireEvent.click(nextButton);
  
    // Vérifier que les images suivantes sont visibles
    expect(screen.getByAltText("Slide 2")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 3")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 4")).toBeInTheDocument();
  });

  it("moves to the previous set of images when prev button is clicked", () => {
    render(<Carousel images={images} visibleCount={3} />);
  
    // Cliquer sur le bouton "suivant" pour avancer
    const nextButton = screen.getByRole('button', { name: /next/i });
    fireEvent.click(nextButton);
  
    // Cliquer sur le bouton "précédent" (Previous)
    const prevButton = screen.getByRole('button', { name: /previous/i }); // Use aria-label
    fireEvent.click(prevButton);
  
    // Vérifier que les images initiales sont visibles
    expect(screen.getByAltText("Slide 1")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 2")).toBeInTheDocument();
    expect(screen.getByAltText("Slide 3")).toBeInTheDocument();
  });



});