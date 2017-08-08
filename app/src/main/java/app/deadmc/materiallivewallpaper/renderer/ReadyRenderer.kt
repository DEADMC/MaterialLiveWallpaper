package app.deadmc.materiallivewallpaper.renderer

import android.content.ContentValues.TAG
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.opengles.GL10


/**
 * Created by adanilov on 08.08.2017.
 */
abstract class ReadyRenderer : GLSurfaceView.Renderer {



    /**
     * If the surface changes, reset the view
     */
    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        var height = height
        if (height == 0) { // Prevent A Divide By Zero By
            height = 1 // Making Height Equal One
        }

        GLES20.glViewport(0, 0, width, height)
    }

    fun loadShader(type: Int, shaderCode: String): Int {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        val shader = GLES20.glCreateShader(type)

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode)
        GLES20.glCompileShader(shader)

        return shader
    }

    companion object {
        fun checkGlError(glOperation: String) {
            var error: Int = 0
            while (error != GLES20.GL_NO_ERROR) {
                error = GLES20.glGetError()
                Log.e(TAG, glOperation + ": glError " + error)
                throw RuntimeException(glOperation + ": glError " + error)
            }
        }
    }

}