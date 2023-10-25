package movement.movementStrategy

import board.Board
import movement.MovementStrategy
import pieceEatingRuler.PieceEatingRuler
import player.Player
import vector.Vector

object HorizontalMovement: MovementStrategy {
    override fun checkMovement(pieceEatingRuler: PieceEatingRuler, player: Player, actual: Vector,
                               destination: Vector, board: Board): Boolean {
        if (!isHorizontal(actual, destination)) return false
        return !checkIfPieceInMiddlePath(actual, destination, board)
    }

    private fun isHorizontal(origin: Vector, destini: Vector): Boolean{
        if (origin.y != destini.y) return false
        if (origin.x == destini.x) return false
        return true
    }

    private fun checkIfPieceInMiddlePath(origin: Vector, destini: Vector, board: Board): Boolean{
        if (origin.x < destini.x) return checkIfPieceBetweenXMinAndXMax(origin.x, destini.x, origin.y, board)
        return checkIfPieceBetweenXMinAndXMax(destini.x, origin.x, origin.y, board)
    }

    private fun checkIfPieceBetweenXMinAndXMax(xMin: Int, xMax: Int, y: Int, board: Board): Boolean{
        for (x in xMin+1 until xMax)
            if (board.getPieceInPosition(Vector(x, y)).isSuccess)
                return true
        return false
    }
}