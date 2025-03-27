class CalculateEmissionService {
    async calculate(startCoordonate, endCoordonate, transport) {
        if (startCoordonate === '' || endCoordonate === '') return -1;
        let emission;
        try {
            const response = await fetch(
              `http://localhost:8089/calculemission/getemission?gpsStart=${startCoordonate}&gpsEnd=${endCoordonate}&transport=${transport}`
            );
    
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`); // Handle HTTP errors
            }
    
            const data = await response.json();
            
            if (data) {
                emission = data;
            }
    
        } catch (err) {
            console.error("Error fetching emission:", err);
        }
        return emission;
    }
	
	
	async calculateDistance(startCoordonate, endCoordonate, transport) {
	        if (startCoordonate === '' || endCoordonate === '') return -1;
	        let distance;
	        try {
	            const response = await fetch(
	              `http://localhost:8089/calculemission/getdistance?gpsStart=${startCoordonate}&gpsEnd=${endCoordonate}&transport=${transport}`
	            );
	    
	            if (!response.ok) {
	              throw new Error(`HTTP error! status: ${response.status}`); // Handle HTTP errors
	            }
	    
	            const data = await response.json();
	            
	            if (data) {
	                distance = data;
	            }
	    
	        } catch (err) {
	            console.error("Error fetching emission:", err);
	        }
	        return distance;
	    }
}

const calculateEmissionService = new CalculateEmissionService();
export default calculateEmissionService;