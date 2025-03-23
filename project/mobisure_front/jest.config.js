module.exports = {
    testEnvironment: 'jsdom', // Pour tester des composants React
    setupFilesAfterEnv: ['<rootDir>/src/setupTests.js'], // Fichier de configuration supplémentaire
    moduleNameMapper: {
      '^react-router-dom$': '<rootDir>/src/__mocks__/react-router-dom.js', // Mapper les modules si nécessaire
    },
  };