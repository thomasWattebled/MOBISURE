class CalculateEmissionService {
    async calculate(startCoordonate, endCoordonate, transport) {
        let emission;
        try {
            const response = await fetch(
              `http://localhost:8080/calculemission/webapi/calculemission/getemission?gpsStart=${startCoordonate}&gpsEnd=${endCoordonate}&transport=${transport}`
            );
    
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`); // Handle HTTP errors
            }
    
            const data = await response.json();
            
            if (data) {
                console.log(data);
                emission = data;
            }
    
        } catch (err) {
            console.error("Error fetching emission:", err);
        }
        return emission;
    }
}

const calculateEmissionService = new CalculateEmissionService();
export default calculateEmissionService;