import React, { useState, ChangeEvent, FormEvent, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

import './style.css';
import api from '../../service/api';

interface IData{
  id: number,
  nome?: string;
  telefone: string;
  celular?: string;
  pessoaid:number;
}

const CadastroContato
: React.FC = () => {
  const history = useHistory();
  const [insertData, setInsertData] = useState<Boolean>(false);
  const [updateGrid, setUpdateGrid] = useState<Boolean>(false);
  const [idSelecionado, setIdSelecionado] = useState<number>(-1);

  const [dataRegister, setDataRegister] = useState({
    nome: '',
    telefone: '',
    celular: ''
  })

  const [data, setData] = useState<[IData]>([{
    id: -1,
    nome:'',
    telefone:'',
    celular:'',
    pessoaid: -1
  }]);
  
  useEffect( () => {
    (
      async() => {
        if (insertData){
          if (idSelecionado <= 0){
            await api.post('/pessoacontato', dataRegister);
            alert('Cadastro efetuado com sucesso');
          }else{
            await api.put('/pessoacontato', dataRegister);
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
          await api.get('/pessoacontato').then(resolve =>{
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
          await api.get(`pessoacontato/${idSelecionado}`).then( resolve => {
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

        <label>Telefone</label>
        <input 
          type="text" 
          placeholder="Telefone"
          name="telefone"
          id="telefone"
          onChange={handleInputChange} 
          value={dataRegister.telefone}
        />

        <label>Celular</label>
        <input 
          type="text" 
          placeholder="Celular"
          name="celular"
          id="celular"
          onChange={handleInputChange}  
          value={dataRegister.celular}
        />

        <button className="buttonAdiconar">Adicionar</button>

        <br/>
        <br/>
        <table>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Telefone</th>
            <th scope="col">Celular</th>
          </tr>

          {
            data.map( item => (
              <tr key={item.id}>
                <td>{item.nome}</td>   
                <td>{item.telefone}</td>   
                <td>{item.celular}</td>   
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
              </tr>
             ) )
          }
        </table>
      </form> 
    </div>
  );
}

export default CadastroContato
;