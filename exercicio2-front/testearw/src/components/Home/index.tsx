import React, { useState, ChangeEvent, FormEvent, useEffect, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import PessoaContext from '../context/pessoaContext';
import api from '../../service/api';

import './style.css';

interface IData{
  id: number,
  nome?: string;
  rg?: string;
  cpf?: string;
  datanascimento?: string;
}


const Home: React.FC = () => {
  const history = useHistory();
  const [insertData, setInsertData] = useState<Boolean>(false);
  const [updateGrid, setUpdateGrid] = useState<Boolean>(false);
  const [idSelecionado, setIdSelecionado] = useState<number>(-1);
  const {AtualizaIdPessoa} = useContext(PessoaContext);

  const [dataRegister, setDataRegister] = useState({
    nome: '',
    rg: '',
    cpf: '',
    datanascimento: ''
  })

  const [data, setData] = useState<[IData]>([{
    id: -1,
    nome:'',
    rg:'',
    cpf:'',
    datanascimento:''
  }]);
  
  useEffect( () => {
    (
      async() => {
        if (insertData){
          if (idSelecionado <= 0){
            await api.post('/pessoa', dataRegister);
            alert('Cadastro efetuado com sucesso');
          }else{
            await api.put('/pessoa', dataRegister);
            alert('Atuazação efetuada com sucesso');
          }
          setInsertData(false);
          setUpdateGrid(true);
          setIdSelecionado(-1);
        }
      }
      )();
    } , [insertData] )

    useEffect( () => {
      (
        async() => (
          await api.get('/pessoa').then(resolve =>{
            setData(resolve.data);
            console.log(resolve.data);
            setUpdateGrid(false);
          })

        )
      )()
    }, [updateGrid] )


    useEffect( () => {
      (
        async() => (
          await api.get(`pessoa/${idSelecionado}`).then( resolve => {
            setDataRegister(resolve.data)            
          })
        )
      )()
    }, [idSelecionado] )    
    
  const handleRegister = (event: FormEvent) => {
    event.preventDefault();

    setInsertData(true);
  }

  const handleInputChange = ( event: ChangeEvent<HTMLInputElement> ) => {
      const {name, value} = event.target;

      setDataRegister({...dataRegister, [name]: value});
  }

  async function handlerEditClick(event: FormEvent<HTMLButtonElement>){
    event.preventDefault();
    
    setIdSelecionado( parseInt(event.currentTarget.value));
  }

  
  async function handlerDeleteClick(event: FormEvent<HTMLButtonElement>){
    event.preventDefault();
    const value = parseInt(event.currentTarget.value); 

    await api.delete(`pessoa/${value}`);
    
    setUpdateGrid(true);
  }  

  const handleClickContato = (event: FormEvent<HTMLButtonElement>) => {
    AtualizaIdPessoa(parseInt(event.currentTarget.value));
    history.push('/cadastrocontato')
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
          value={dataRegister.nome}
        />

        <label>Rg</label>
        <input 
          type="text" 
          placeholder="Rg"
          name="rg"
          id="rg"
          onChange={handleInputChange} 
          value={dataRegister.rg}
        />

        <label>Cpf</label>
        <input 
          type="text" 
          placeholder="Cpf"
          name="cpf"
          id="cpf"
          onChange={handleInputChange}  
          value={dataRegister.cpf}
        />

        <label>Data nascimento</label>
        <input 
          type="text" 
          placeholder="Data nascimento"
          name="datanascimento"
          id="datanascimento"
          onChange={handleInputChange}  
          value={dataRegister.datanascimento}
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
              <tr key={item.id}>
                <td>{item.nome}</td>   
                <td>{item.rg}</td>   
                <td>{item.cpf}</td>   
                <td>{item.datanascimento}</td>  
                <td>
                  <button 
                      onClick={handlerEditClick}  
                      value={item.id} 
                      className="buttonGrid" >
                        Editar
                  </button>
                </td> 

                <td>
                  <button 
                    onClick={handlerDeleteClick}  
                    value={item.id} 
                    className="buttonGrid" >
                      Deletar
                   </button>
                </td> 
                    
                <td>
                  <button 
                    value={item.id} 
                    className="buttonGrid"
                    onClick={handleClickContato}>
                    Contatos
                  </button>
                </td> 
              </tr>
             ) )
          }
        </table>
      </form> 
    </div>
  );
}

export default Home;