package app.deadmc.materiallivewallpaper.model

import android.opengl.GLES20
import android.opengl.Matrix
import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer
import app.deadmc.materiallivewallpaper.util.squareCoords
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

open class Square(renderer: ReadyRenderer, triangleCoords:FloatArray = squareCoords) : Figure(renderer)  {

    private val vertexBuffer: FloatBuffer

    // Set color with red, green, blue and alpha (opacity) values
    val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)
    val COORDS_PER_VERTEX = 3


    private var mProgram: Int = 0
    private var mPositionHandle: Int = 0
    private var mColorHandle: Int = 0
    private var mMVPMatrixHandle: Int = 0

    private val vertexCount:Int = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex


    init {
        val bb = ByteBuffer.allocateDirect(
                triangleCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(triangleCoords)
        vertexBuffer.position(0)

        val vertexShader = renderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode)
        val fragmentShader = renderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode)
        mProgram = GLES20.glCreateProgram()
        GLES20.glAttachShader(mProgram, vertexShader)
        GLES20.glAttachShader(mProgram, fragmentShader)
        GLES20.glLinkProgram(mProgram)
    }

    override fun draw() {
        slowTranslate()
        Matrix.scaleM(mMVPMatrix,0,scale,scale,1f)
        Matrix.translateM(mMVPMatrix, 0, sourceX+translateX, sourceY+translateY, 0f)
        //unchanged painting start
        GLES20.glUseProgram(mProgram)
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer)
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        GLES20.glDisableVertexAttribArray(mPositionHandle)
        //unchanged painting end
        Matrix.translateM(mMVPMatrix, 0, -translateX-sourceX, -translateY-sourceY, 0f)
        Matrix.scaleM(mMVPMatrix,0,1/scale,1/scale,1f)
    }



}