import React from 'react';
import {Route, BrowserRouter, Switch} from 'react-router-dom';
import Home from './components/Home';

// import { Container } from './styles';

const Routes: React.FC = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route component={Home} path="/" exact />
      </Switch>
    </BrowserRouter>

  )
}

export default Routes;