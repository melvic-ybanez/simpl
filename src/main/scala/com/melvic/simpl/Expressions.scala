package com.melvic.simpl

/**
  * Created by melvic on 3/26/17.
  */

sealed trait Expression

case class Symbol(name: String) extends Expression
case class Literal[A](value: A) extends Expression
case class Binary[A, B, C](operator: String, left: Expression, right: Expression) extends Expression
case class Unary[A, B](operator: String, operand: Expression) extends Expression
case class IfElse(condition: Boolean, ifTrue: Expression, ifFalse: Expression) extends Expression
case class Loop(condition: Boolean, body: Expression) extends Expression
case class Function(name: String, parameters: List[Expression], body: Expression) extends Expression
case class Block(body: List[Expression]) extends Expression
case class Call(symbol: Symbol) extends Expression

