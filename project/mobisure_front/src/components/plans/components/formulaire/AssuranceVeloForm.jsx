import React, { useState } from "react";
import "../../style/form.css";

const AssuranceVeloForm = () =>  {
      const [selectedOption, setSelectedOption] = useState("");
    
    return (
        <div className="form-container">
          <h2>Formulaire Assurance Vélo</h2>
          <form>
            <div className="form-group" >
          <label>
          <p>Electrique ? </p>
          </label>
          <div className="radio-group">
          <input type="radio" id="oui" name="oui" value="oui" checked={selectedOption === "oui"}
                onChange={(e) => setSelectedOption(e.target.value)}/>
          <label for="oui">Oui</label>
          <input type="radio" id="non" name="non" value="non" checked={selectedOption === "non"}
                onChange={(e) => setSelectedOption(e.target.value)}/>
          <label for="non">Non</label>
              </div>
            </div>
            
            <button type="submit">Soumettre</button>
          </form>
        </div>
        
    
      );
    
};
export default AssuranceVeloForm;
    
    
    


