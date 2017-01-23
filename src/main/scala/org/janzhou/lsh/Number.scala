package org.janzhou.LSH

trait Number[T] {
  def T:T
  def +(x: T): T
  def -(x: T): T
  def *(x: T): T
  def >(x: T): Boolean
  def <(x: T): Boolean
}

class NumberDouble(val self:Double) extends Number[Double] {
  def T = self
  def +(x: Double): Double = self + x
  def -(x: Double): Double = self - x
  def *(x: Double): Double = self * x
  def >(x: Double): Boolean = self > x
  def <(x: Double): Boolean = self < x
}

class NumberFloat(val self:Float) extends Number[Float] {
  def T = self
  def +(x: Float): Float = self + x
  def -(x: Float): Float = self - x
  def *(x: Float): Float = self * x
  def <(x: Float): Boolean = self < x
  def >(x: Float): Boolean = self > x
}

class NumberLong(val self:Long) extends Number[Long] {
  def T = self
  def +(x: Long): Long = self + x
  def -(x: Long): Long = self - x
  def *(x: Long): Long = self * x
  def <(x: Long): Boolean = self < x
  def >(x: Long): Boolean = self > x
}

class NumberInt(val self:Int) extends Number[Int] {
  def T = self
  def +(x: Int): Int = self + x
  def -(x: Int): Int = self - x
  def *(x: Int): Int = self * x
  def <(x: Int): Boolean = self < x
  def >(x: Int): Boolean = self > x
}

class NumberByte(val self:Byte) extends Number[Byte] {
  def T = self
  def +(x: Byte): Byte = (self + x).asInstanceOf[ Byte ]
  def -(x: Byte): Byte = (self - x).asInstanceOf[ Byte ]
  def *(x: Byte): Byte = (self * x).asInstanceOf[ Byte ]
  def <(x: Byte): Boolean = self < x
  def >(x: Byte): Boolean = self > x
}

object Number {
  def dot[T <% Number[T]](a: Iterable[T], b: Iterable[T])(implicit zero: Number[T]):T = {
    a.zip(b).map({ case (x, y) => x*y }).foldLeft(zero.T)(_+_)
  }
}

trait NumberConversions {

  import scala.language.implicitConversions

  implicit class NumberArray[T <% Number[T]](val self: Array[T])(implicit zero: Number[T]){
    def *(that: Array[T]):T = dot(that)
    def dot(that: Array[T]):T = Number.dot(self, that)
  }

  implicit class NumberIterable[T <% Number[T]](val self: Iterable[T])(implicit zero: Number[T]){
    def *(that: Iterable[T]):T = dot(that)
    def dot(that: Iterable[T]):T = Number.dot(self, that)
  }

  implicit def Double2DumberDouble(x:Double):Number[Double] = new NumberDouble(x)
  implicit def NumberDouble2Double(x:NumberDouble):Double = x.T
  implicit object ZeroDouble extends NumberDouble(0)

  implicit def Float2NumberFloat(x:Float):Number[Float] = new NumberFloat(x)
  implicit def NumberFloat2Float(x:NumberFloat):Float = x.T
  implicit object ZeroFloat extends NumberFloat(0)

  implicit def Long2NumberLong(x:Long):Number[Long] = new NumberLong(x)
  implicit def NumberLong2Long(x:NumberLong):Long = x.T
  implicit object ZeroLong extends NumberLong(0)

  implicit def Int2NumberInt(x:Int):Number[Int] = new NumberInt(x)
  implicit def NumberInt2Int(x:NumberInt):Int = x.T
  implicit object ZeroInt extends NumberInt(0)

  implicit def Byte2NumberByte(x:Byte):Number[Byte] = new NumberByte(x)
  implicit def NumberByte2Byte(x:NumberByte):Byte = x.T
  implicit object ZeroByte extends NumberByte(0)
}