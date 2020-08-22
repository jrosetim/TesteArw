import React from 'react';
import {Route, BrowserRouter, Switch} from 'react-router-dom';
import Home from './components/Home';
import CadastroContato from './components/CadastroContato';

// import { Container } from './styles';

const Routes: React.FC = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route component={Home} path="/" exact />
        <Route component={CadastroContato} path="/cadastrocontato" exact />

      </Switch>
    </BrowserRouter>

  )
}

export default Routes;