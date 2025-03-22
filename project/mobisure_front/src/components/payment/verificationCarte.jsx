// verification_carte.jsx
export const validateCardNumber = (cardNumber) => {
    // Algorithme de Luhn
    let sum = 0;
    for (let i = 0; i < cardNumber.length; i++) {
        let digit = parseInt(cardNumber[i]);
        if ((cardNumber.length - i) % 2 === 0) {
            digit *= 2;
            if (digit > 9) digit -= 9;
        }
        sum += digit;
    }
    return sum % 10 === 0;
};

export const validateCVV = (cvv) => {
    // CVV doit être composé de 3 ou 4 chiffres
    return /^\d{3,4}$/.test(cvv);
};