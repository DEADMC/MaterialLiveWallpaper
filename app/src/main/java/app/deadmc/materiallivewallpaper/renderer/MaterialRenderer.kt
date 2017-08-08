package app.deadmc.materiallivewallpaper.renderer

import android.hardware.SensorEvent
import android.opengl.GLES20
import android.opengl.Matrix
import android.util.Log
import app.deadmc.materiallivewallpaper.model.Square
import app.deadmc.materiallivewallpaper.model.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MaterialRenderer : ReadyRenderer() {

    val squareArrayList = ArrayList<Square>()

    private val mVPMatrix = FloatArray(16)
    private val mMVPMatrix = FloatArray(16)
    private val mProjectionMatrix = FloatArray(16)
    private val mViewMatrix = FloatArray(16)
    private lateinit var square: Square
    private lateinit var triangle: Triangle

    init {
        /*
        for (i in 1..360) {
            squareArrayList.add(Square(this@MaterialRenderer))
        }
        */
    }

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        square = Square(this@MaterialRenderer)
        triangle = Triangle(this@MaterialRenderer)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        Log.e("tag","width "+width+" height "+height)
        val ratio = width.toFloat() / height
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 2f, 7f)
    }

    override fun onDrawFrame(unused: GL10) {
        // Clear Screen And Depth Buffer
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // Set the camera position (View matrix)

        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)


        //Matrix.setIdentityM(mModelMatrix, 0); // initialize to identity matrix
        //Matrix.scaleM(mModelMatrix,0,2f,2f,2f)
        //Matrix.translateM(mModelMatrix, 0, -0.5f, 0f, 0f)
        //Matrix.glLoadIdentity() // Reset The Current Modelview Matrix
        //Matrix.glTranslatef(-1.5f, -2.6f, -6.0f)
        Matrix.translateM(mMVPMatrix,0,0.1f,0.1f,0.1f)

        square.draw(mVPMatrix)
        //triangle.draw(mMVPMatrix)
        //drawSquares()
        Log.e("MaterialRenderer", "onDrawFrame")
        /*
        gl.glTranslatef(0.0f, 2.5f, 0.0f) // Move up 2.5 Units
        triangle.draw(gl) // Draw the triangle
        */

    }

    fun drawSquares() {
        var x = 0.22f
        var y = 0.22f

        squareArrayList.forEach {
            it.draw(mVPMatrix) // Draw the square
            x+=0.22f
            if (x > 0.22f*15f) {
                x = 0.22f
                y+=0.22f
            }
           // Matrix.translateM(mModelMatrix, 0, -0.5f, 0f, 0f)


        }

    }




    fun release() {
        // TODO stuff to release
    }

    fun onSensorChanged(event: SensorEvent?) {

    }


}