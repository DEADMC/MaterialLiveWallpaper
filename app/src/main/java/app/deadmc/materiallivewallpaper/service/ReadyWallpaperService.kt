package app.deadmc.materiallivewallpaper.service

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.opengl.GLSurfaceView
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer


abstract class ReadyWallpaperService : WallpaperService() {
    val TAG = this.javaClass.simpleName


    override fun onCreateEngine(): WallpaperService.Engine {
        return WallpaperEngine()
    }

    abstract fun getNewRenderer(): ReadyRenderer

    inner class WallpaperEngine : WallpaperService.Engine(), SensorEventListener {
        var renderer: ReadyRenderer? = null
        lateinit private var glSurfaceView: WallpaperGLSurfaceView
        lateinit var sensorManager: SensorManager

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
            glSurfaceView = WallpaperGLSurfaceView(this@ReadyWallpaperService)
            glSurfaceView.setEGLContextClientVersion(2)
            glSurfaceView.preserveEGLContextOnPause = true
            // glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
            renderer = getNewRenderer()
            glSurfaceView.setRenderer(renderer)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)

            if (visible) {
                glSurfaceView.onResume()
                onResume()
            } else {
                glSurfaceView.onPause()
                onPause()
            }

        }

        fun onResume() {
            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_GAME)
            Log.e(TAG, "onResume() called")
        }

        fun onPause() {
            sensorManager.unregisterListener(this)
        }

        override fun onDestroy() {
            super.onDestroy()
            if (renderer != null) {
                renderer!!.release()
            }
            renderer = null
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

        override fun onSensorChanged(event: SensorEvent?) {
            renderer?.onSensorChanged(event)
            /*
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {

            }
            */
        }



        internal inner class WallpaperGLSurfaceView(context: Context) : GLSurfaceView(context) {

            override fun getHolder(): SurfaceHolder {
                return getSurfaceHolder()
            }

            fun onDestroy() {
                super.onDetachedFromWindow()
            }
        }
    }


}

