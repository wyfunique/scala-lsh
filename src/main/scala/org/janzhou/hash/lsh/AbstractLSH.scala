package org.janzhou.hash.LSH

import org.janzhou.hash._
import org.janzhou.hash.Number._

abstract class LSH[T <% Number[T]] extends HASH[T]
abstract class SignatureLSH[T <% Number[T]] extends SignatureHASH[T]