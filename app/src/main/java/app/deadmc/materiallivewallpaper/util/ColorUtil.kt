package app.deadmc.materiallivewallpaper.util

import javax.microedition.khronos.opengles.GL10

/**
 * Created by adanilov on 26.07.2017.
 */
fun setColorARGB(gl: GL10, color:String) {
    val a = Integer.valueOf(color.substring(1, 3), 16)!!
    val r  = Integer.valueOf(color.substring(3, 5), 16)!!
    val g = Integer.valueOf(color.substring(5, 7), 16)!!
    val b = Integer.valueOf(color.substring(7, 9), 16)!!
    gl.glColor4f(r/255f,g/255f,b/255f,a/255f)
}

fun setColorRGB(gl: GL10, color:String) {
    val r = Integer.valueOf(color.substring(1, 3), 16)!!
    val g  = Integer.valueOf(color.substring(3, 5), 16)!!
    val b = Integer.valueOf(color.substring(5, 7), 16)!!
    gl.glColor4f(r/255f,g/255f,b/255f,1f)
}