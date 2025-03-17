import {React, useState } from 'react';

import { validateCardNumber, validateCVV } from './verificationCarte';

const PaymentForm = () => {
    const [cardNumber, setCardNumber] = useState('');
    const [cardHolder, setCardHolder] = useState('');
    const [cvv, setCvv] = useState('');
    const [message, setMessage] = useState('');

    const [cardNumberError, setCardNumberError] = useState('');
    const [cvvError, setCvvError] = useState('');

    const amount = 100.0; // A MODIFIER

    const handleCardNumberChange = (e) => {
        const value = e.target.value;
        setCardNumber(value);

        if (value && !validateCardNumber(value)) {
            setCardNumberError('Invalid card number');
        } else {
            setCardNumberError('');
        }
    };

    const handleCVVChange = (e) => {
        const value = e.target.value;
        setCvv(value);

        if (value && !validateCVV(value)) {
            setCvvError('Invalid CVV');
        } else {
            setCvvError('');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (cardNumberError || cvvError) {
            setMessage('Please fix the errors before submitting.');
            return;
        }

        const paymentRequest = {
            cardNumber,
            cardHolder,
            cvv,
            amount 
        };

        try {
            const response =await fetch("http://localhost:8088/api/tryPayment", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    cardNumber: "1234567890123456",
                    cardHolder: "John Doe",
                    cvv: "123",
                    amount: 100.0
                })
            })

            const result = await response.text();
            setMessage(result);
        } catch (error) {
            setMessage('fail');
        }
    };

    return (
        <div>
            <h2>Simulate Payment</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Card Number:</label>
                    <input
                        type="text"
                        value={cardNumber}
                        onChange={handleCardNumberChange}
                        required
                    />
                    {cardNumberError && <p style={{ color: 'red' }}>{cardNumberError}</p>}
                </div>
                <div>
                    <label>Card Holder:</label>
                    <input
                        type="text"
                        value={cardHolder}
                        onChange={(e) => setCardHolder(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>CVV:</label>
                    <input
                        type="text"
                        value={cvv}
                        onChange={handleCVVChange}
                        required
                    />
                    {cvvError && <p style={{ color: 'red' }}>{cvvError}</p>}
                </div>
                <div>
                    <label>Amount:</label>
                    <input
                        type="number"
                        value={amount}
                        readOnly 
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    )
};

export default PaymentForm;