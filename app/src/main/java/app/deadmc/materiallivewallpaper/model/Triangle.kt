package app.deadmc.materiallivewallpaper.model

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10


/**
 * Created by adanilov on 26.07.2017.
 */
class Triangle {

    /** The buffer holding the vertices  */
    private val vertexBuffer: FloatBuffer

    /** The initial vertex definition  */
    private val vertices = floatArrayOf(
            0.0f, 1.0f, 0.0f, // Top
            -1.0f, -1.0f, 0.0f, // Bottom Left
            1.0f, -1.0f, 0.0f  // Bottom Right
    )

    /**
     * The Triangle constructor.

     * Initiate the buffers.
     */
    init {
        //
        val byteBuf = ByteBuffer.allocateDirect(vertices.size * 4)
        byteBuf.order(ByteOrder.nativeOrder())
        vertexBuffer = byteBuf.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)
    }

    /**
     * The object own drawing function. Called from the renderer to redraw this
     * instance with possible changes in values.

     * @param gl
     * *            - The GL context
     */
    fun draw(gl: GL10) {
        // Set the face rotation
        gl.glFrontFace(GL10.GL_CCW)

        // Point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)

        // Enable vertex buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)

        // Draw the vertices as triangle strip
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.size / 3)

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
    }
}