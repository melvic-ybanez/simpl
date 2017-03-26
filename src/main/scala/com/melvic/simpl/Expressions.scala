package com.melvic.simpl

/**
  * Created by melvic on 3/26/17.
  */

sealed trait Expression

sealed trait Literal[+A] {
  def value: A
}

case class Symbol(name: String) extends Expression

// Literals
case class IntLiteral(value: Int) extends Literal[Int]
case class LongLiteral(value: Long) extends Literal[Long]
case class FloatLiteral(value: Float) extends Literal[Float]
case class DoubleLiteral(value: Double) extends Literal[Double]
case class BooleanLiteral(value: Boolean) extends Literal[Boolean]
case class StringLiteral(value: String) extends Literal[String]
case object NoneLiteral extends Literal[Nothing] {
  def value = throw new IllegalAccessException("Unable to access member 'value'")
}

// Compound expressions
case class Binary[A, B, C](operator: String, left: Expression, right: Expression) extends Expression
case class Unary[A, B](operator: String, operand: Expression) extends Expression

// Conditionals and Loops
case class IfElse(condition: Boolean, ifTrue: Expression, ifFalse: Expression) extends Expression
case class Loop(condition: Boolean, body: Expression) extends Expression

// Functions and Blocks
case class Function(name: String, parameters: List[Expression], body: Expression) extends Expression
case class Block(body: List[Expression]) extends Expression
case class Call(symbol: Symbol) extends Expression



