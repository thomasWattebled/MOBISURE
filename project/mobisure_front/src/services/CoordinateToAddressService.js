class CoordinateToAddress {
    async translateToAddress(latitude, longitude) {
        try {
            const response = await fetch(
                `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}`
            );

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            if (data.error) {
                throw new Error(data.error);
            }

            if (data.address) {
                return {
                    formatted: data.display_name || "Adresse inconnue",
                    country: data.address.country || "Pays inconnu",
                    city: data.address.city || data.address.town || data.address.village || "Ville inconnue"
                };
            } else {
                return {
                    formatted: "Aucune adresse trouvée.",
                    country: "Pays inconnu",
                    city: "Ville inconnue"
                };
            }
        } catch (err) {
            console.error("Error fetching address:", err);
            return {
                formatted: "Erreur lors de la récupération de l'adresse",
                country: "Pays inconnu",
                city: "Ville inconnue"
            };
        }
    }
}

const coordinateToAddressInstance = new CoordinateToAddress();
export default coordinateToAddressInstance;
