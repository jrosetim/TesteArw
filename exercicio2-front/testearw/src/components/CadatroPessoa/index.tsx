import React from 'react';

// import { Container } from './styles';

const CadatroPessoa: React.FC = () => {
  return (
    <div className="painel">
      <label>Nome</label>
      <input type="text" placeholder="Nome"/>

      <label>Rg</label>
      <input type="text" placeholder="Rg"/>

      <label>Cpf</label>
      <input type="text" placeholder="Cpf"/>

      <label>Data nascimento</label>
      <input type="text" placeholder="Data nascimento"/>
    </div>

  );
}

export default CadatroPessoa;