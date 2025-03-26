const SERVER_URL = "http://localhost:8087";

class SinistreService {
    
  async fetchAllSinistres (userId) {
      return await fetch(`${SERVER_URL}/sinistres/userId/${userId}`);
  }

  async postCarSinitre (data) {
    return await fetch(`${SERVER_URL}/sinistres/car/save`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data)
    })
  }

  async postHealthSinitre (data) {
    return await fetch(`${SERVER_URL}/sinistres/health/save`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data)
    })
  }

  async postSinistreFile (file, sinistreId) {
    const formData = new FormData();
    formData.append('file', file);
    return await fetch(`${SERVER_URL}/sinistres/file/${sinistreId}`, {
      method: 'POST',
      body: formData
    })
  }

  async getSinistreFile (sinistreId) {
    return await fetch(`${SERVER_URL}/sinistres/file/${sinistreId}`);
  }

  async getHealthSinistre (sinistreId) {
    return await fetch(`${SERVER_URL}/sinistres/health/${sinistreId}`);
  }

  async getCarSinistre (sinistreId) {
    return await fetch(`${SERVER_URL}/sinistres/car/${sinistreId}`);
  }

  async deleteSinistre (sinistreId) {
    return await fetch(`${SERVER_URL}/sinistres/${sinistreId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      }
    })
  }

  buildFile (fileInfo) {
    const base64Data = fileInfo.fileData.split(',')[1] || fileInfo.fileData;

    const byteString = atob(base64Data); // Decode Base64

    // Convert byte string to Uint8Array
    const byteArray = new Uint8Array(byteString.length);
    for (let i = 0; i < byteString.length; i++) {
      byteArray[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([byteArray], { type: fileInfo.contentType });

    return new File([blob], fileInfo.filename, { type: fileInfo.contentType, lastModified: Date.now() });
  }

}

export default new SinistreService();