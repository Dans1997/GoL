package br.unb.cic.poo.gol

object CellsRep {


    val max_height = Main.height
    val max_width = Main.width
    val collection = Array.ofDim[Cell](max_height, max_width)

    for(i <- (0 until max_height)) {
      for(j <- (0 until max_width)) {
        collection(i)(j) = new Cell
      }
    }

    def apply(height: Int, width: Int): Cell = {
      val i = positiveModulo(height, max_height)
      val j = positiveModulo(width, max_width)
      collection(i)(j)
    }

    private def positiveModulo(num: Int, den: Int): Int = {
      val resultado = num % den
      if (resultado >= 0) { resultado } else { resultado + den }
    }

    def clear: Unit ={
      for(i <- 0 until max_height) {
        for (j <- 0 until max_width) {
          CellsRep(i, j).kill
        }
      }
    }

    def save: UndoMemento = {
      new UndoMemento(max_height, max_width, collection)
    }

    def restore(memento: UndoMemento) = {
      for (i<- (0 until max_height)) {
        for (j <- (0 until max_width)) {
          if(memento.collection(i)(j).isAlive) {
            collection(i)(j).revive
          }else {
            collection(i)(j).kill
          }
        }
      }
    }


}
