package br.unb.cic.poo.gol

/*
 * Regras clássicas do jogo
 */

class OldRule extends GameEngine{
  def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (cells(i)(j).isAlive) &&
      (GameController.gameEngine.numberOfNeighborhoodAliveCells(i, j) == 2 || GameController.gameEngine.numberOfNeighborhoodAliveCells(i, j) == 3)
  }
  
  def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) && 
      (GameController.gameEngine.numberOfNeighborhoodAliveCells(i, j) == 3)
  }
}