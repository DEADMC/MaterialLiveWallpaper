package app.deadmc.materiallivewallpaper.renderer

import android.hardware.SensorEvent
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.opengles.GL10

abstract class ReadyRenderer : GLSurfaceView.Renderer {

    val mMVPMatrix = FloatArray(16)
    val mProjectionMatrix = FloatArray(16)
    val mViewMatrix = FloatArray(16)


    /**
     * If the surface changes, reset the view
     */
    override fun onSurfaceChanged(gl: GL10, width: Int, heightPass: Int) {
        var height = heightPass
        if (height == 0) { // Prevent A Divide By Zero By
            height = 1 // Making Height Equal One
        }

        GLES20.glViewport(0, 0, width, height)
    }

    abstract fun onSensorChanged(event: SensorEvent?)

    fun loadShader(type: Int, shaderCode: String): Int {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        val shader = GLES20.glCreateShader(type)
        GLES20.glShaderSource(shader, shaderCode)
        GLES20.glCompileShader(shader)

        return shader
    }

    fun release() {
        // TODO stuff to release
    }

}