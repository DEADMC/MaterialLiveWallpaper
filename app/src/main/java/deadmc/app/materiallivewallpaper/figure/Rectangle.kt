package deadmc.app.materiallivewallpaper.figure

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * Created by adanilov on 18.07.2017.
 */
class Rectangle {
    val paint = Paint()
    val shadowPaint = Paint()
    val baseX = 1f
    val baseY = 1f
    val shadowSize = 20f
    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        paint.isAntiAlias = true
        paint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.parseColor("#20000000"))
    }

    fun drawFigure(canvas: Canvas, addX:Float, addY:Float, sizeX:Float, sizeY:Float, color:Int) {
        val right = baseX*sizeX+addX
        val bottom = baseY*sizeY+addY
        paint.color = color
        canvas.drawRect(addX,addY,right,bottom,paint)
        //canvas.drawRect()
    }

}