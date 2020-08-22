import React, {createContext, useState} from 'react';

interface iPessoaData{
  id: number;
  AtualizaIdPessoa(id: number): Promise<void>;
}

const PessoaContext = createContext<iPessoaData>({} as iPessoaData);

export const PessoaContextProvider: React.FC = ({children}) => {
  const [pessoaIdContext, setPessoaIdContext] = useState<number>(-1);

  // try {
  //   const AtualizaIdPessoa = (id: number) => {
  //     setPessoaIdContext(id);
  //   }
  // } catch (error) {
  //   console.log(erro);
  // }


  async function AtualizaIdPessoa(id: number){
    setPessoaIdContext(id);
  }

  return (
    <PessoaContext.Provider value={
      {
        id: pessoaIdContext,
        AtualizaIdPessoa
      }
    }>
      {children}
    </PessoaContext.Provider>
  )

}


export default PessoaContext;