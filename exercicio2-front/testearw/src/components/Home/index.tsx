import React, { useState, ChangeEvent, FormEvent, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

import './style.css';
import api from '../../service/api';
import { resolve } from 'dns';

interface IData{
  nome?: string;
  rg?: string;
  cpf?: string;
  datanascimento?: string;
}


const Home: React.FC = () => {
  const history = useHistory();
  const [insertData, setInsertData] = useState<Boolean>(false);

  const [dataRegister, setDataRegister] = useState({
    nome: '',
    rg: '',
    cpf: '',
    datanascimento: ''
  })

  const [data, setData] = useState<[IData]>([{
    nome:'',
    rg:'',
    cpf:'',
    datanascimento:''
  }]);


  useEffect( () => {
    (
      async() => (
        await api.get('/pessoa').then(resolve =>{
          setData(resolve.data);
          console.log(resolve.data);
        })
      )
    )()

  }, [] )

  useEffect( () => {
    (
      async() => {
        if (insertData){
          await api.post('/pessoa', dataRegister);

          alert('Cadastro efetuado com sucesso');

          setInsertData(false);
        }
      }
    )();
  } , [insertData] )

  const handleRegister = (event: FormEvent) => {
    event.preventDefault();

    setInsertData(true);
  }

  const handleInputChange = ( event: ChangeEvent<HTMLInputElement> ) => {
      const {name, value} = event.target;

      setDataRegister({...dataRegister, [name]: value});
  }

  return (
    <div className="container">     
      <form onSubmit={handleRegister} className="formulario">
        <label>Nome</label>
        <input 
          type="text" 
          placeholder="Nome"
          name="nome"
          id="nome"
          onChange={handleInputChange}  
        />

        <label>Rg</label>
        <input 
          type="text" 
          placeholder="Rg"
          name="rg"
          id="rg"
          onChange={handleInputChange} 
        />

        <label>Cpf</label>
        <input 
          type="text" 
          placeholder="Cpf"
          name="cpf"
          id="cpf"
          onChange={handleInputChange}  
        />

        <label>Data nascimento</label>
        <input 
          type="text" 
          placeholder="Data nascimento"
          name="datanascimento"
          id="datanascimento"
          onChange={handleInputChange}  
        />  

        <button className="buttonAdiconar">Adicionar</button>

        <br/>
        <br/>
        <table>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Rg</th>
            <th scope="col">Cpf</th>
            <th scope="col">Data nascimento</th>
          </tr>

          {
            data.map( item => (
              <tr key={item.cpf}>
                <td>{item.nome}</td>   
                <td>{item.rg}</td>   
                <td>{item.cpf}</td>   
                <td>{item.datanascimento}</td>  
                <td><button className="buttonGrid">Editar</button></td> 
                <td><button className="buttonGrid">Contatos</button></td> 
              </tr>
             ) )
          }
        </table>
        {/* <th>
          {
            data.map( item => (
              <td key={item.cpf}>
                {item.nome}
              </td>
            ) )    

          }
        </th> */}
        
      </form> 
    </div>
  );
}

export default Home;