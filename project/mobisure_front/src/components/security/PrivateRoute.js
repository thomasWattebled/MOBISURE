import React from 'react'
import { Navigate } from 'react-router-dom'
import { useAuth } from '../auth/AuthContext'

/**
 * Component used to protect some nested component according to user authentification.
 * 
 * If the user is authenticated, the nested components are rendered. Otherwise, a redirection is done to the route "/".
 * 
 * @param {Object} props 
 * @param {JSX[]} props.children - Nested children.
 * @returns 
 */
export function PrivateRoute({ children }) {
  const { userIsAuthenticated } = useAuth()
  return userIsAuthenticated() ? children : <Navigate to="/" />
}

/**
 * Children of this component are rendered if user is authenticated.
 * 
 * If the user is authenticated, the nested components are rendered. Otherwise, a redirection is done to the route "/".
 * 
 * @param {Object} props 
 * @param {JSX[]} props.children - Nested children.
 * @returns 
 */
export function WhenUserIsAuthenticated({ children }) {
  const { userIsAuthenticated } = useAuth()

  return userIsAuthenticated() ? children : <></>
}

export function WhenUserIsNotAuthenticated({ children }) {
  const { userIsAuthenticated } = useAuth()

  return !userIsAuthenticated() ? children : <></>
}

/**
 * Children of this component are rendered if user is in specified role.
 * 
 * If the user is authenticated, the nested components are rendered. Otherwise, a redirection is done to the route "/".
 * 
 * @param {Object} props 
 * @param {JSX[]} props.children - Nested children.
 * @param {String} props.role - Required role
 * @returns 
 */
export function WhenUserIsInRole({ children, role }) {
  const { userIsInRole } = useAuth()

  return userIsInRole(role) ? children : <></>
}

/**
 * Children of this component are rendered if user is in specified role.
 * 
 * If the user is authenticated, the nested components are rendered. Otherwise, a redirection is done to the route "/".
 * 
 * @param {Object} props 
 * @param {JSX[]} props.children - Nested children.
 * @param {String[]} props.roles - A list of roles
 * @returns 
 */
export function WhenUserHasAnyRole({ children, roles }) {
  const { userHasAnyRole } = useAuth()
  return userHasAnyRole(roles) ? children : <></>
}

export default PrivateRoute
