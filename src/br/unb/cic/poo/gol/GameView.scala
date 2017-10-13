package br.unb.cic.poo.gol

import scala.io.StdIn.{readInt, readLine}

/**
 * Representa o componente View do GoL
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameView {
  
	private final val LINE = "+-----+"
	private final val DEAD_CELL = "|     |"
	private final val ALIVE_CELL = "|  o  |"
	
	private final val INVALID_OPTION = 0
	private final val MAKE_CELL_ALIVE = 1
	private final val NEXT_GENERATION = 2
	private final val HALT = 3
	
	/*
	 * Adicionada uma nova opção - RULES - para selecionar a regra de Conway
	 * ou a nova regra 
	 */
	
	private final val RULES = 4
	
  /**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualização do jogo.
	 */
	def update {
		printFirstRow
		printLine
		
		for(i <- (0 until Main.height)) {
		  for(j <- (0 until Main.width)) {
		    print(if (GameController.gameEngine.isCellAlive(i, j))  ALIVE_CELL else DEAD_CELL);
		  }
		  println("   " + i)
		  printLine
		}
		printOptions
	}
  
  private def printOptions {
	  
	  var option = 0
	  println("\n\n")
	  
	  do{
	    println("Select one of the options: \n \n"); 
			println("[1] Make a cell alive");
			println("[2] Next generation");
			println("[3] Halt");
			
			/*
			 * Imprime uma nova opção de selecionar qual regra se deseja jogar
			 */
			
			println("[4] Select Rule");
		
			print("\n \n Option: ");
			
			option = parseOption(readLine)
	  }while(option == 0)
	  
	  option match {
      case MAKE_CELL_ALIVE => makeCellAlive
      case NEXT_GENERATION => nextGeneration
      case HALT => halt
      case RULES => printRules
    }
	}
  
  private def makeCellAlive {
	  
	  var i = 0
	  var j = 0
	  
	  do {
      print("\n Inform the row number (0 - " + (Main.height - 1) + "): ")
      i = readInt
      
      print("\n Inform the column number (0 - " + (Main.width - 1) + "): ")
      j = readInt
      
    } while(!validPosition(i,j))
      
    GameController.makeCellAlive(i, j)
	}

  private def nextGeneration = GameController.nextGeneration
  private def halt = GameController.halt
	
  private def validPosition(i: Int, j: Int): Boolean = {
		println(i);
		println(j);
		i >= 0 && i < Main.height && j >= 0 && j < Main.width
	}
  
	def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => HALT
    case "4" => RULES
    case _ => INVALID_OPTION
  }
	
  
  /* Imprime uma linha usada como separador das linhas do tabuleiro */
	private def printLine() {
	  for(j <- (0 until Main.width)) {
	    print(LINE)
	  }
	  println()
	}
  
  /*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private def printFirstRow {
		println("\n \n");
		
		for(j <- (0 until Main.width)) {
		  print("   " + j + "   ")
		}
		println()
	}
	
	/*
	 * Essa função será responsável por imprimir o menu de regras 
	 * possiveis de serem jogadas no jogo
	 * 
	 * O menu foi implementado dessa forma para facilitar a adição de
	 * novas regras - se for o caso, só será necessário modificar essa parte do código
	 */
	
	private def printRules {
	  
	  var optionRules = 0
	  var cellsAux = Array.ofDim[Cell](Main.height, Main.width)
	  
	  println("\n\n")
	  
	  do{
	    println("Select one of the rules: \n \n"); 
			println("[1] Classic Conway's");
			println("[2] The Two Neighbor Rule");
		
			print("\n \n Option: ");
			
			optionRules = parseOptionRules(readLine)
	  }while(optionRules == 0)
	  
	  optionRules match {
      case 1 => {
                  println("\n You chose the Classic Game of Life. \n");
                  cellsAux = GameController.gameEngine.cells
                  GameController.gameEngine = new OldRule
                  GameController.gameEngine.cells = cellsAux
                  update
                }
      case 2 => {
                  println("\n You chose the Two Neighbor Rule. \n");
                  cellsAux = GameController.gameEngine.cells
                  GameController.gameEngine = new NewRule
                  GameController.gameEngine.cells = cellsAux
                  update
                }
    }
	}
	
	/*
	 * A priori, essa função é redundante e poderia ser facilmente
	 * retirada. Contudo, levando em conta a facilidade de adição de
	 * novas regras, fazer uma função de fácil entendimento e
	 * manutenção é completamente necessário!
	 */
	def parseOptionRules(option: String): Int = option match {
    case "1" => 1 
    case "2" => 2
    case _ => INVALID_OPTION
  }
  
}