import React, { createContext, useContext, useState, useEffect } from 'react'

const AuthContext = createContext()

function AuthProvider({ children}) {
 	
	const[user, setUser] =useState(null)
	
	useEffect(() =>{
		const storedUser=JSON.parse(localStorage.getItem('user'))
		setUser(storedUser)
	}, [])
	
	const getUser=() =>{
		return JSON.parse(localStorage.getItem('user'))
	}
	
	const userLogin = (userData) => {
		setUser(userData);
	    localStorage.setItem('user', JSON.stringify(userData));
	};

	const userLogout = () => {
		setUser(null);
	    localStorage.removeItem('user');
	};
	
	const userIsAuthenticated = () => {
	    let storedUser = localStorage.getItem('user')
	    if (!storedUser) {
	      return false
	    }

	    return true
	  }
	  
	  const userIsInRole = (role) => {
	      const storedUser = localStorage.getItem('user')
	      if (!storedUser) {
	        return false
	      }

	      const userDetails = JSON.parse( storedUser)
	      console.log( "userDetails : " + JSON.stringify(userDetails))
	      if( userDetails?.roles.some( r => r === role ) ) {
	        return true
	      }
	      return false
	    }
		
		const userHasAnyRole = (roles) => {
		    let storedUser = localStorage.getItem('user')
		    if (!storedUser) {
		      return false
		    }

		    const userDetails = JSON.parse( storedUser)
		    if( userDetails.roles.some( r1 => 
		      roles.some( r2 => r1===r2 )  
		      )) {
		      return true
		    }
		    return false
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
	
	return(
		<AuthContext.Provider value={contextValue}>
			{children}
		</AuthContext.Provider>
	)
 }

 export function useAuth() {
   	return useContext(AuthContext)
   }
   
   export { AuthProvider };








