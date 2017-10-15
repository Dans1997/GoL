package br.unb.cic.poo.gol
  import scala.collection.mutable.ListBuffer
object CellsCare {
  val cellsMemento = new ListBuffer[UndoMemento]
  private var current = -1

  def persist: Unit ={
      cellsMemento += CellsRep.save
      current += 1
    }
    def undo: Unit = {
      if (validMemento(current - 1)) {
        current -= 1
        CellsRep.restore(cellsMemento(current))
      } else {
        CellsRep.restore(cellsMemento(0))

      }
    }


    def redo: Unit = {
      if (validMemento(current + 1)) {
        current += 1
        CellsRep.restore(cellsMemento(current))
      } else {

        CellsRep.restore(cellsMemento.last)
      }
    }

    private def validMemento(position: Int): Boolean = {
      position >= 0 && position < cellsMemento.size
    }
  }
