package com.melvic.simpl

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

/**
  * Created by melvic on 3/26/17.
  */
class SimpLParser extends JavaTokenParsers {
  def symbol: Parser[Symbol] = ident ^^ Symbol
  def integer: Parser[IntLiteral] = wholeNumber ^^ (x => IntLiteral(x.toInt))
  def long: Parser[LongLiteral] = wholeNumber <~ "[lL]".r ^^ (x => LongLiteral(x.toLong))
  def float: Parser[FloatLiteral] = floatingPointNumber <~ "[fF]".r ^^ (x => FloatLiteral(x.toFloat))
  def double: Parser[DoubleLiteral] = floatingPointNumber ^^ (x => DoubleLiteral(x.toDouble))
  def boolean: Parser[BooleanLiteral] = "true|false".r ^^ (x => BooleanLiteral(x.toBoolean))
  def character: Parser[CharacterLiteral] = "'" ~> ".".r <~ "'" ^^ (c => CharacterLiteral(c.head))

  def nil: Parser[ListLiteral] = "Nil" ^^ (_ => NilList)
  def concat: Parser[ListLiteral] = (expression <~ ':') ~ list ^^ {
    case head ~ tail => ConcatList(head, tail)
  }
  def list: Parser[ListLiteral] = nil | concat

  def string: Parser[ListLiteral] = stringLiteral ^^ { str =>
    val text = str.tail.init
    text.reverse.foldLeft[ListLiteral](NilList)((acc, c) => ConcatList(CharacterLiteral(c), acc))
  }

  def none: Parser[Literal[Nothing]] = "None".r ^^ (str => NoneLiteral)
  def expression: Parser[Expression] = symbol | integer | long | float | double |
    boolean | string | none | list ^^ identity
}
