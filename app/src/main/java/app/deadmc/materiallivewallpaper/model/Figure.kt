package app.deadmc.materiallivewallpaper.model

import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer

open class Figure(renderer: ReadyRenderer) {

    val renderer : ReadyRenderer
    val mMVPMatrix : FloatArray
    var scale = 1f
    var translateX = 0f
    var translateY = 0f
    var sourceX = 0f
    var sourceY = 0f

    val vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}"

    val fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}"

    init {
        this.renderer = renderer
        mMVPMatrix = renderer.mMVPMatrix
    }

    fun slowTranslate() {
        if (translateX > 0) {
            if (translateX - 0.01f > 0)
                translateX -= 0.01f
            else
                translateX = 0f
        }

        if (translateX < 0) {
            if (translateX + 0.01f < 0)
                translateX += 0.01f
            else
                translateX = 0f
        }

        if (translateY > 0) {
            if (translateY - 0.01f > 0)
                translateY -= 0.01f
            else
                translateY = 0f
        }

        if (translateY < 0) {
            if (translateY + 0.01f < 0)
                translateY += 0.01f
            else
                translateY = 0f
        }

    }




}