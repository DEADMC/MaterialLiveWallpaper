package app.deadmc.materiallivewallpaper.renderer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.opengl.GLES20
import android.opengl.Matrix
import android.util.Log
import app.deadmc.materiallivewallpaper.model.Square
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10



class MaterialRenderer : ReadyRenderer() {

    val squareArrayList = ArrayList<Square>()
    val offset = 2.4f
    val defaultX = -11f
    val defaultY = -20f
    var lastUpdate = 0L
    var lastX = 0f
    var lastY = 0f
    var lastZ = 0f
    val SHAKE_THRESHOLD = 800

    init {



    }

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        var x = 0f
        var y = 0f
        for (i in 1..440) {
            val square = Square(this@MaterialRenderer)
            square.scale = 0.1f
            if (x > offset*15f) {
                x = 0f
                y+=offset
            }
            square.sourceX = x+defaultX
            square.sourceY = y+defaultY
            x+=offset
            squareArrayList.add(square)
        }

    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        Log.e("tag","width "+width+" height "+height)
        val ratio = width.toFloat() / height
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    override fun onDrawFrame(unused: GL10) {
        // Clear Screen And Depth Buffer
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // Set the camera position (View matrix)

        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -6f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)

        //Matrix.setIdentityM(mModelMatrix, 0); // initialize to identity matrix
        //Matrix.scaleM(mMVPMatrix,0,0.1f,0.1f,1f)
        //Matrix.scaleM(mMVPMatrix,0,0.7f,0.7f,1f)
        //Matrix.translateM(mModelMatrix, 0, -0.5f, 0f, 0f)
        //Matrix.glLoadIdentity() // Reset The Current Modelview Matrix
        //Matrix.glTranslatef(-1.5f, -2.6f, -6.0f)
        //Matrix.translateM(mMVPMatrix,0,0.1f,0.1f,0.1f)
        drawSquares()
        //
        // Log.e("MaterialRenderer", "onDrawFrame")


    }


    fun drawSquares() {
        squareArrayList.forEach {

            it.draw()
        }

    }





    fun release() {
        // TODO stuff to release
    }

    fun onSensorChanged(event: SensorEvent?) {
        Log.e("tag","called ")
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val curTime = System.currentTimeMillis()
            if (curTime - lastUpdate > 100) {
                val diffTime = curTime - lastUpdate
                lastUpdate = curTime

                val speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000
                Log.e("tag","speed "+speed)
                if (speed > SHAKE_THRESHOLD) {
                    squareArrayList.forEach {

                        it.translateX = Random().nextFloat()
                        it.translateY = Random().nextFloat()
                    }
                }

                lastX = x
                lastY = y
                lastZ = z
            }

        }
    }


}