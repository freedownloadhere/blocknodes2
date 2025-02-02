package com.github.freedownloadhere.blocknodes2.util

import kotlin.math.PI

private const val toDeg = 180.0f / PI.toFloat()

fun Float.eToDegrees() : Float {
    return this * toDeg
}

fun Float.eClampAngle180() : Float {
    var ans = this
    while(ans < -180.0f)
        ans += 360.0f
    while(ans > 180.0f)
        ans -= 360.0f
    return ans
}