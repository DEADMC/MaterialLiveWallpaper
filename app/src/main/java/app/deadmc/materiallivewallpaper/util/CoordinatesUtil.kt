package app.deadmc.materiallivewallpaper.util

/**
 * Created by adanilov on 18.08.2017.
 */
val squareCoords = floatArrayOf(
//first triangle
         -1.0f,  1.0f, 0.0f,
                   -1.0f, -1.0f, 0.0f,
                    1.0f,  1.0f, 0.0f,
//second triangle
                   -1.0f, -1.0f, 0.0f,
                    1.0f, -1.0f, 0.0f,
                    1.0f, 1.0f, 0.0f)

private val size = 1.0f
private val moveBack = -1.0f
val cubeCoords = floatArrayOf(
        ////////////////////////////////////////////////////////////////////
        // FRONT
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        -size, size, size-moveBack, // top-left
        -size, -size, size-moveBack, // bottom-left
        size, -size, size-moveBack, // bottom-right
        // Triangle 2
        size, -size, size-moveBack, // bottom-right
        size, size, size-moveBack, // top-right
        -size, size, size-moveBack, // top-left
        ////////////////////////////////////////////////////////////////////
        // BACK
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        -size, size, -size-moveBack, // top-left
        -size, -size, -size-moveBack, // bottom-left
        size, -size, -size-moveBack, // bottom-right
        // Triangle 2
        size, -size, -size-moveBack, // bottom-right
        size, size, -size-moveBack, // top-right
        -size, size, -size-moveBack, // top-left

        ////////////////////////////////////////////////////////////////////
        // LEFT
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        -size, size, -size-moveBack, // top-left
        -size, -size, -size-moveBack, // bottom-left
        -size, -size, size-moveBack, // bottom-right
        // Triangle 2
        -size, -size, size-moveBack, // bottom-right
        -size, size, size-moveBack, // top-right
        -size, size, -size-moveBack, // top-left
        ////////////////////////////////////////////////////////////////////
        // RIGHT
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        size, size, -size-moveBack, // top-left
        size, -size, -size-moveBack, // bottom-left
        size, -size, size-moveBack, // bottom-right
        // Triangle 2
        size, -size, size-moveBack, // bottom-right
        size, size, size-moveBack, // top-right
        size, size, -size-moveBack, // top-left

        ////////////////////////////////////////////////////////////////////
        // TOP
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        -size, size, -size-moveBack, // top-left
        -size, size, size-moveBack, // bottom-left
        size, size, size-moveBack, // bottom-right
        // Triangle 2
        size, size, size-moveBack, // bottom-right
        size, size, -size-moveBack, // top-right
        -size, size, -size-moveBack, // top-left
        ////////////////////////////////////////////////////////////////////
        // BOTTOM
        ////////////////////////////////////////////////////////////////////
        // Triangle 1
        -size, -size, -size-moveBack, // top-left
        -size, -size, size-moveBack, // bottom-left
        size, -size, size-moveBack, // bottom-right
        // Triangle 2
        size, -size, size-moveBack, // bottom-right
        size, -size, -size-moveBack, // top-right
        -size, -size, -size-moveBack // top-left
)