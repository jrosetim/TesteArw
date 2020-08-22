import React from 'react';
import Routes from './routes';

import './app.css';
import { PessoaContextProvider } from './components/context/pessoaContext';

function App() {
  return (
    <PessoaContextProvider>
      <div className="App">
        <Routes />
      </div>
    </PessoaContextProvider>
  );
}

export default App;
