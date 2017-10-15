package br.unb.cic.poo.gol

/*
 * Em Java, o método Strategy comumente utiliza interfaces para implementar
 * o código. Em Scala, há várias soluções para a não existência de interfaces;
 * Uma delas é utilizar Traits como substituição das interfaces, o que foi
 * escolhido neste código.
 */

trait Strategy {
  def shouldKeepAlive(i: Int, j: Int) : Boolean
  def shouldRevive(i: Int, j: Int) : Boolean
}

class Classic extends Strategy {
  /* verifica se uma celula deve ser mantida viva */
  override def shouldKeepAlive(i: Int, j: Int) : Boolean = {
    (GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 2
        || GameEngine.numberOfNeighborhoodAliveCells(i, j) == 3)
  }

  /* verifica se uma celula deve (re)nascer */
  override def shouldRevive(i: Int, j: Int) : Boolean = {
    (!GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 3)
  }

}

class Expander extends Strategy {
  /* verifica se uma celula deve ser mantida viva */
  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 1)
  }

  /* verifica se uma celula deve (re)nascer */
  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 1)
  }
}