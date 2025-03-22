import React from 'react';

const FormField = ({ id, label, type, name, value, placeholder, handleChange, required = false, pattern }) => (
  <div className="mb-3">
    <label htmlFor={id} className="form-label">
      {label}
    </label>
    <input
      type={type}
      id={id}
      name={name}
      value={value}
      onChange={handleChange}
      className="form-control"
      placeholder={placeholder}
      required={required}
      pattern={pattern}
    />
  </div>
);

export default FormField;