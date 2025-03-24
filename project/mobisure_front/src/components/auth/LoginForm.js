import React, { useState } from 'react';
import {  Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import '../../assets/css/form.css';


export const LoginForm = ({handleLogin, message}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        handleLogin( username, password )
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">Connexion Mobisure</div>
                        <div className="card-body">
                            {message && <div className="alert alert-danger">{message}</div>}
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label>Adresse mail</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Mot de passe</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                            </form>
                            <div className="mt-3">
                                <span>Pas de compte ? <Link to="/register">créer un compte</Link></span>
                            </div>
							<div className="mt-3">
								<span>Mot de passe oublié ? <Link to="/changeMdp">réinitialiser le mot de passe</Link></span>
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginForm;




