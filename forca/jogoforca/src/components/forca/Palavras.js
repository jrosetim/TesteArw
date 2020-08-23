var palavras = [
  "kravmaga",
  "elotech",
  "baiano",
  "java",
  "angular",
  "react",
  "javascript",
  "palmeiras"
]

function palavraRandom(){
  return palavras[Math.floor(Math.random() * palavras.length)]
}

export {palavraRandom};

