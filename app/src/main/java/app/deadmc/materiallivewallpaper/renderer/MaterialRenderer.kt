package app.deadmc.materiallivewallpaper.renderer

import android.hardware.SensorEvent
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.GLU
import android.opengl.Matrix
import android.util.Log
import app.deadmc.materiallivewallpaper.model.Square
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


/**
 * Created by adanilov on 25.07.2017.
 */
class MaterialRenderer : GLSurfaceView.Renderer {

    val squareArrayList = ArrayList<Square>()

    init {
        for (i in 1..360) {
            squareArrayList.add(Square())
        }
    }

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.5f) // Black Background
        GLES20.glClearDepthf(1.0f) // Depth Buffer Setup
        GLES20.glEnable(GL10.GL_DEPTH_TEST) // Enables Depth Testing
        GLES20.glDepthFunc(GL10.GL_LEQUAL) // The Type Of Depth Testing To Do

        // Really Nice Perspective Calculations
        GLES20.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)

    }

    override fun onDrawFrame(unused: GL10) {
        // Clear Screen And Depth Buffer
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        Matrix.glLoadIdentity() // Reset The Current Modelview Matrix
        GLES20.glTranslatef(-1.5f, -2.6f, -6.0f)
        drawSquares(gl)
        Log.e("MaterialRenderer", "onDrawFrame")
        /*
        gl.glTranslatef(0.0f, 2.5f, 0.0f) // Move up 2.5 Units
        triangle.draw(gl) // Draw the triangle
        */

    }

    fun drawSquares(gl: GL10) {
        var x = 0.22f
        var y = 0.22f

        squareArrayList.forEach {
            gl.glPushMatrix()
            gl.glTranslatef(x, y, 0.0f) // Move down 1.2 Unit And Into The Screen 6.0
            it.draw(gl) // Draw the square
            x+=0.22f
            if (x > 0.22f*15f) {
                x = 0.22f
                y+=0.22f
            }
            gl.glPopMatrix()



        }

    }

    /**
     * If the surface changes, reset the view
     */
    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        var height = height
        if (height == 0) { // Prevent A Divide By Zero By
            height = 1 // Making Height Equal One
        }

        gl.glViewport(0, 0, width, height) // Reset The Current Viewport
        gl.glMatrixMode(GL10.GL_PROJECTION) // Select The Projection Matrix
        gl.glLoadIdentity() // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(gl, 45.0f, width.toFloat() / height.toFloat(), 0.1f,
                100.0f)

        gl.glMatrixMode(GL10.GL_MODELVIEW) // Select The Modelview Matrix
        gl.glLoadIdentity() // Reset The Modelview Matrix
    }


    fun release() {
        // TODO stuff to release
    }

    fun onSensorChanged(event: SensorEvent) {

    }
}