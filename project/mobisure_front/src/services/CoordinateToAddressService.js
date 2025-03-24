class CoordinateToAddress {
    async translateToAddress (latitude, longitude) {
        let address;
        try {
            const response = await fetch(
              `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}`
            );
    
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`); // Handle HTTP errors
            }
    
            const data = await response.json();
    
            if (data.error) {
              throw new Error(data.error);
            }
            
            if (data) {
              address = data.display_name;
            } else {
              address = "No address found for these coordinates.";
            }
    
        } catch (err) {
                console.error("Error fetching address:", err);
        }
        return address;
    }
}

const coordinateToAddressInstance = new CoordinateToAddress();
export default coordinateToAddressInstance;