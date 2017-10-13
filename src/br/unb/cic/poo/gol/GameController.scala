package br.unb.cic.poo.gol

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  
  /*
   * Aqui, pode-se notar a inicialização de uma variável do tipo 
   * GameEngine, que será usada para instanciar tal classe abstrata
   * 
   * Por padrão, ela abrigará as regras clássicas do Game of Life 
   * para não ter a necessidade de entrar na seleção de regras
   * todas as vezes que o programa for rodado
   */
  
  var gameEngine : GameEngine = new OldRule
  
  def start {
    GameView.update
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			gameEngine.makeCellAlive(i, j)
			GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    gameEngine.nextGeneration
    GameView.update
  }
  
}