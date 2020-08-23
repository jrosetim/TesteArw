import React, { Component } from "react";
import { palavraRandom } from "./Palavras";

import './style.css';

class Forca extends Component {
  static defaultProps = {
    qtdeChances: 6,
  };

  constructor(props) {
    super(props);
    this.state = { erros: 0, acerto: new Set(), reposta: palavraRandom() };
    this.verificaLetra = this.verificaLetra.bind(this);
    this.reset = this.reset.bind(this);
  }

  reset() {
    this.setState({
      erros: 0,
      acerto: new Set(),
      reposta: palavraRandom()
    });
  }

  verificaPalavra() {
    return this.state.reposta
      .split("")
      .map(ltr => (this.state.acerto.has(ltr) ? ltr : "_"));
  }

  verificaLetra(evt) {
    let ltr = evt.target.value;
    this.setState(st => ({
      acerto: st.acerto.add(ltr),
      erros: st.erros + (st.reposta.includes(ltr) ? 0 : 1)
    }));
  }

  gerarBotoes() {
    return "abcdefghijklmnopqrstuvwxyz".split("").map(ltr => (
      <button
        className="buttonsLetras"
        key={ltr}
        value={ltr}
        onClick={this.verificaLetra}
        disabled={this.state.acerto.has(ltr)}
      >
        {ltr}
      </button>
    ));
  }

  /** render: render game */
  render() {
    const gameOver = this.state.erros >= this.props.qtdeChances;
    const venceu = this.verificaPalavra().join("") === this.state.reposta;
    let gameState = this.gerarBotoes();
    if (venceu) gameState = "Você Venceu!!!";
    if (gameOver) gameState = "Perdeu playboy!";
    return (
      <div>
        <h1>Jogo da Forca</h1>
        
        <p >Tentativas: {this.state.erros} </p>
        <p >
          {!gameOver ? this.verificaPalavra() : this.state.reposta}
        </p>
        <p >{gameState}</p>
        <button className="buttonReset" onClick={this.reset}>
          Recomeçar
        </button>
      </div>
    );
  }
}

export default Forca;
