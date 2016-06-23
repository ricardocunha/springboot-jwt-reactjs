import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, IndexRoute, browserHistory } from 'react-router';
import reduxThunk from 'redux-thunk';

import App from './components/app';
import Signin from './components/auth/auth.signin';
import Signout from './components/auth/auth.signout';
import Signup from './components/auth/auth.signup';
import RequireAuth from './components/auth/auth.require';
import Feature from './components/user/user.main';
import DashBoard from './components/dash';

import reducers from './reducers';
import { AUTH_USER } from './actions/auth/types';

const createStoreWithMiddleware = applyMiddleware(reduxThunk)(createStore);
const store = createStoreWithMiddleware(reducers);
const token = localStorage.getItem('token');
if (token){
  store.dispatch({type: AUTH_USER });
}
ReactDOM.render(
  <Provider store={store}>
    <Router history={browserHistory}>
      <Route path="/" component={App}>
        <IndexRoute component={DashBoard}/>
        <Route path="signin" component={Signin} />
        <Route path="signout" component={Signout} />
        <Route path="signup" component={Signup} />
        <Route path="feature" component={RequireAuth(Feature)} />
      </Route>

    </Router>
  </Provider>
  , document.querySelector('.container'));
