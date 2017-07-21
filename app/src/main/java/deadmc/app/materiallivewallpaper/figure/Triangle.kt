package deadmc.app.materiallivewallpaper.figure

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path


/**
 * Created by adanilov on 18.07.2017.
 */
class Triangle {
    val paint = Paint()
    val shadowPaint = Paint()
    val shadowSize = 20f

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        paint.isAntiAlias = true
        paint.setShadowLayer(10.0f, -10.0f, -10.0f, Color.parseColor("#20000000"))
    }

    fun drawFigure(canvas: Canvas, topX:Float,topY:Float,botX:Float,botY:Float,midX:Float,midY:Float, color: Int) {
        paint.color = color

        val path = Path()
        path.moveTo(topX,topY)
        path.lineTo(botX,botY)
        path.lineTo(midX,midY)
        path.close()
        canvas.drawPath(path, paint)
        //canvas.drawRect()
    }

}