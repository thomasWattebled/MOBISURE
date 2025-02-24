import React, {useState} from 'react';
import calculateEmissionService from '../../services/CalculateEmissionService';

const CalculateEmission = ({startCoordonate, endCoordonate, transport}) => {
    const [emissions, setEmissions] = useState(-1);

    return (
        <>
            <button
                onClick={() => {setEmissions(calculateEmissionService.calculate(startCoordonate, endCoordonate, transport))}}
            >
                Calculate Emissions
            </button>
            {emissions !== -1 ?
            <p>
                Emission : {emissions} kg
            </p>
             : 
            <p>

            </p>}
        </>
    )
}

export default CalculateEmission;