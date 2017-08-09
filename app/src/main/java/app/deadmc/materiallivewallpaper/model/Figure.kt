package app.deadmc.materiallivewallpaper.model

import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer

open class Figure(renderer: ReadyRenderer) {

    val renderer : ReadyRenderer
    val mMVPMatrix : FloatArray
    var scale = 1f
    var translateX = 0f
    var translateY = 0f

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




}