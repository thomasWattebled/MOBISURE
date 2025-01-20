import React, { createContext, useContext, useState, useEffect } from 'react'

const AuthContext = createContext()

function AuthProvider({ children }) {
    const [user, setUser] = useState(null)

    // Vérifier si l'utilisateur est connecté au moment du rendu initial
    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'))
        if (storedUser) {
            setUser(storedUser)
        }
    }, [])

    // Récupérer les données de l'utilisateur depuis le localStorage
    const getUser = () => {
        return JSON.parse(localStorage.getItem('user'))
    }

    // Connexion de l'utilisateur et sauvegarde des données dans localStorage
    const userLogin = (userData) => {
        setUser(userData)
        localStorage.setItem('user', JSON.stringify(userData))
    }

    // Déconnexion de l'utilisateur et suppression des données de localStorage
    const userLogout = () => {
        setUser(null)
        localStorage.removeItem('user')
    }

    // Vérifier si l'utilisateur est authentifié
    const userIsAuthenticated = () => {
        const storedUser = localStorage.getItem('user')
        return storedUser ? true : false
    }

    // Vérifier si l'utilisateur a un rôle spécifique
    const userIsInRole = (role) => {
        const storedUser = localStorage.getItem('user')
        if (!storedUser) return false

        const userDetails = JSON.parse(storedUser)
        return userDetails?.roles?.includes(role) ?? false
    }

    // Vérifier si l'utilisateur a l'un des rôles spécifiés
    const userHasAnyRole = (roles) => {
        const storedUser = localStorage.getItem('user')
        if (!storedUser) return false

        const userDetails = JSON.parse(storedUser)
        return roles.some((role) => userDetails.roles.includes(role))
    }

    const contextValue = {
        user,
        getUser,
        userIsAuthenticated,
        userIsInRole,
        userHasAnyRole,
        userLogin,
        userLogout,
    }

    return (
        <AuthContext.Provider value={contextValue}>
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth() {
    return useContext(AuthContext)
}

export { AuthProvider }
