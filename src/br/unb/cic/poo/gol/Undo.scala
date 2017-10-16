package br.unb.cic.poo.gol

class UndoMemento(height: int , width: int , cells: Array[Array[Cell ]] ){
  val collection = Array.ofDim[Cell](height , width)
  for(i <- (0 until height)){
    for( j <- (0 until width)){
      collection(i)(j) = new Cell
      if(cells(i)(j).isAlive)){collection(i)(j).revive}
    }
  }
}
