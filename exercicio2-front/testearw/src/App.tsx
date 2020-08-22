import React from 'react';
import MenuLateral from './components/MenuLateral';
import CadatroPessoa from './components/CadatroPessoa';

import './app.css';

function App() {
  return (
    <div className="App">
      <MenuLateral />
      
      <CadatroPessoa />
    </div>
  );
}

export default App;
