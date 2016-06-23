import axios from 'axios';
import { browserHistory } from 'react-router';
import {
  AUTH_USER,
  UNAUTH_USER,
  AUTH_ERROR,
  FETCH_MESSAGE
} from './types';

const ROOT_URL = 'http://localhost:9080';

//Um Action Creator sempre retorna um objeto, e objeto sempre chamará uma action e a action tem um tipo
export function signinUser({ email, password }) {
  return function(dispatch){
    axios.post(`${ROOT_URL}/signin/auth`, { email, password })
    .then(response => {
      dispatch({type: AUTH_USER});
      console.log(response.data);
      localStorage.setItem('token',response.data.token);
      localStorage.setItem('name',response.data.name);

      browserHistory.push('/feature');
    })
    .catch(()=>{
      dispatch(authError('Bad Login Info '));
    });
  }
}
export function authError(error) {
  return {
    type: AUTH_ERROR,
    payload: error
  };
}
export function signoutUser(){
  localStorage.removeItem('token');
  return { type: UNAUTH_USER };
}

export function signupUser({ email, password }){
  return function(dispatch){
    axios.post(`${ROOT_URL}/signup/signup`,{ email, password})
    .then(response => {
      dispatch({type: AUTH_USER});
      localStorage.setItem('token',response.data.token);
      browserHistory.push('/feature');
    })
    .catch(response=>{
            console.log(response);
      dispatch(authError(response.data.error));
    });
  }
}

export function fetchMessage(){
  return function(dispatch){
    axios.get(ROOT_URL,{
      headers: { authorization: localStorage.getItem('token') }
    })
    .then(response => {
      dispatch({
        type: FETCH_MESSAGE,
        payload: response.data.message
      });
    });

  }
}
