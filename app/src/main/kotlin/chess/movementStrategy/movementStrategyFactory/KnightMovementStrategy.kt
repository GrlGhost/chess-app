package chess.movementStrategy.movementStrategyFactory

import boardGame.movement.MovementValidator
import boardGame.movement.MovementStrategyFactory
import chess.movementStrategy.validators.EmptyOrEatEnemyInDestiny
import chess.movementStrategy.validators.LJumpMovement
import boardGame.movement.unionMovement.AndUnionMovementValidator

//TODO: consider making the factories actual strategies
object KnightMovementStrategy: MovementStrategyFactory {
    private val strategy: MovementValidator by lazy {
        AndUnionMovementValidator(listOf(EmptyOrEatEnemyInDestiny, LJumpMovement))
    }
    override fun getMovementStrategy(): MovementValidator {
        return strategy;
    }
}