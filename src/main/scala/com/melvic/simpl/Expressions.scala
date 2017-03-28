package com.melvic.simpl

/**
  * Created by melvic on 3/26/17.
  */

sealed trait Expression

sealed trait Literal[+A] extends Expression {
  def value: A
}

case class Symbol(name: String) extends Expression

// Literals
case class IntLiteral(value: Int) extends Literal[Int]
case class LongLiteral(value: Long) extends Literal[Long]
case class FloatLiteral(value: Float) extends Literal[Float]
case class DoubleLiteral(value: Double) extends Literal[Double]
case class BooleanLiteral(value: Boolean) extends Literal[Boolean]
case class CharacterLiteral(value: Character) extends Literal[Character]
case object NoneLiteral extends Literal[Nothing] {
  def value = throw new IllegalAccessException("Unable to access member 'value'")
}
sealed trait ListLiteral extends Expression
case object NilList extends ListLiteral
case class ConcatList(head: Expression, tail: ListLiteral) extends ListLiteral

// Compound expressions
case class Binary(operator: String, left: Expression, right: Expression) extends Expression
case class Unary(operator: String, operand: Expression) extends Expression

// Conditionals and Loops
case class IfElse(condition: Boolean, ifTrue: Expression, ifFalse: Expression) extends Expression
case class Loop(condition: Boolean, body: Expression) extends Expression

// Functions and Blocks
case class Function(name: String, parameters: List[Expression], body: Expression) extends Expression
case class Block(body: List[Expression]) extends Expression
case class Call(symbol: Symbol, parameters: List[Expression]) extends Expression



