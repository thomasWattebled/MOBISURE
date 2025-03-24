import React from 'react';

const CoordinateInput = ({coordinateName, coordinateValue, isFocusedFunct, isFocused}) => {

    const handleInputClick = () => {
        isFocusedFunct();
    };
    
    return (
        <>
            <label htmlFor={coordinateName}>{coordinateName}</label>
            <input
                type="text" 
                id={coordinateName} 
                name={coordinateName} 
                size="40"
                value={coordinateValue}
                readOnly 
                required
                onClick={handleInputClick}
                style={{
                    border: isFocused ? '2px solid black' : '1px solid gray',
                    padding: '8px',
                    borderRadius: '4px',
                    transition: 'border-color 0.3s ease',
                }}
            />
        </>
    )
  }

  export default CoordinateInput