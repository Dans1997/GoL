package br.unb.cic.poo.gol

/*
 * Novas regras do jogo
 */

class NewRule extends GameEngine {
   def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (cells(i)(j).isAlive) &&
      (GameController.gameEngine.numberOfNeighborhoodAliveCells(i, j) == 1)
  }
  
  def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) && 
      (GameController.gameEngine.numberOfNeighborhoodAliveCells(i, j) == 2)
  }
}