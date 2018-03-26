package app.deadmc.materiallivewallpaper.model

import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer

abstract class Figure(renderer: ReadyRenderer) {

    val renderer : ReadyRenderer
    val mMVPMatrix : FloatArray
    var scale = 1f
    var translateX = 0f
    var translateY = 0f
    var sourceX = 0f
    var sourceY = 0f
    var speedX = 0.2f
    var speedY = 0.2f

    val vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}"

    val vertexBlurShaderCode = "" +
            "uniform mat4 u_projView;" +
            "attribute vec2 Position;" +
            "attribute vec2 TexCoord;" +
            "attribute vec4 Color;" +
            "varying vec4 vColor;" +
            "varying vec2 vTexCoord;" +
            "void main() {" +
            "vColor = Color;" +
            "vTexCoord = TexCoord;" +
            "gl_Position = u_projView * vec4(Position, 0.0, 1.0);" +
            "}"

    val fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}"

    val fragmenBlurShaderCode =
            "precision mediump float;" +
            "varying vec4 vColor;" +
            "varying vec2 vTexCoord;" +
            "uniform sampler2D u_texture;" +
            "uniform float resolution;" +
            "uniform float radius;" +
            "uniform vec2 dir;" +
            "void main() {" +
            "vec4 sum = vec4(0.0);" +
            "vec2 tc = vTexCoord;" +
            "float blur = radius/resolution; " +
            "float hstep = dir.x;" +
            "float vstep = dir.y;" +
            "sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) * 0.0162162162;" +
            "sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) * 0.0540540541;" +
            "sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) * 0.1216216216;" +
            "sum += texture2D(u_texture, vec2(tc.x - 1.0*blur*hstep, tc.y - 1.0*blur*vstep)) * 0.1945945946;" +
            "sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.2270270270;" +
            "sum += texture2D(u_texture, vec2(tc.x + 1.0*blur*hstep, tc.y + 1.0*blur*vstep)) * 0.1945945946;" +
            "sum += texture2D(u_texture, vec2(tc.x + 2.0*blur*hstep, tc.y + 2.0*blur*vstep)) * 0.1216216216;" +
            "sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y + 3.0*blur*vstep)) * 0.0540540541;" +
            "sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y + 4.0*blur*vstep)) * 0.0162162162;" +
            "gl_FragColor = vColor * vec4(sum.rgb, 1.0);" +
            "}"

    init {
        this.renderer = renderer
        mMVPMatrix = renderer.mMVPMatrix
    }

    fun slowTranslate() {
        speedX = Math.abs(translateX)/16
        if (translateX > 0) {
            if (translateX - speedX > 0)
                translateX -= speedX
            else
                translateX = 0f
        }

        if (translateX < 0) {
            if (translateX + speedX < 0)
                translateX += speedX
            else
                translateX = 0f
        }

        speedY = Math.abs(translateY)/16
        if (translateY > 0) {
            if (translateY - speedY > 0)
                translateY -= speedY
            else
                translateY = 0f
        }

        if (translateY < 0) {
            if (translateY + speedY< 0)
                translateY += speedY
            else
                translateY = 0f
        }

    }

    abstract fun draw()



}