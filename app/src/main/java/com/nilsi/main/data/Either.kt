package com.main.data

sealed class Either<out E, out V> {
  data class Left<out E>(val left: E) : Either<E, Nothing>()
  data class Right<out V>(val right: V) : Either<Nothing, V>()
}