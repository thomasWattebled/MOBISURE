import { validateCardNumber, validateCVV } from '../../components/payment/verificationCarte';

describe("validateCardNumber", () => {
  it("returns true for valid card numbers", () => {
    // Valid card numbers (examples)
    expect(validateCardNumber("4111111111111111")).toBe(true); // Visa
    expect(validateCardNumber("5500000000000004")).toBe(true); // Mastercard
    expect(validateCardNumber("378282246310005")).toBe(true);  // American Express
  });

  it("returns false for invalid card numbers", () => {
    // Invalid card numbers
    expect(validateCardNumber("4111111111111112")).toBe(false); // Invalid Visa
    expect(validateCardNumber("5500000000000005")).toBe(false); // Invalid Mastercard
    expect(validateCardNumber("378282246310006")).toBe(false);  // Invalid American Express
    expect(validateCardNumber("1234567890123456")).toBe(false); // Random invalid number
  });

  it("returns false for non-numeric input", () => {
    // Non-numeric input
    expect(validateCardNumber("4111-1111-1111-1111")).toBe(false); // Contains hyphens
    expect(validateCardNumber("4111 1111 1111 1111")).toBe(false); // Contains spaces
    expect(validateCardNumber("ABCDEFGHIJKLMNOP")).toBe(false);    // Alphabetic characters
  });

  it("returns false for empty input", () => {
    // Empty input
    expect(validateCardNumber("")).toBe(false);
  });
});

describe("validateCVV", () => {
  it("returns true for valid CVV", () => {
    // Valid CVV (3 or 4 digits)
    expect(validateCVV("123")).toBe(true);  // 3 digits
    expect(validateCVV("1234")).toBe(true); // 4 digits
  });

  it("returns false for invalid CVV", () => {
    // Invalid CVV
    expect(validateCVV("12")).toBe(false);    
    expect(validateCVV("12345")).toBe(false); 
    expect(validateCVV("ABC")).toBe(false);   
    expect(validateCVV("12A")).toBe(false);   
    expect(validateCVV("12 3")).toBe(false);  
    expect(validateCVV("12-3")).toBe(false);  
  });

  it("returns false for empty input", () => {
    expect(validateCVV("")).toBe(false);
  });
});