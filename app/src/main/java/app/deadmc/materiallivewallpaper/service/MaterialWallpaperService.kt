package app.deadmc.materiallivewallpaper.service


import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import app.deadmc.materiallivewallpaper.opengles_classes.GLWallpaperService
import app.deadmc.materiallivewallpaper.renderer.MaterialRenderer



class MaterialWallpaperService : GLWallpaperService() {
    val TAG = this.javaClass.simpleName


    override fun onCreateEngine(): WallpaperService.Engine {
        return WallpaperEngine()
    }



    internal inner class WallpaperEngine : GLEngine(),SensorEventListener {
        var renderer: MaterialRenderer? = null
        lateinit var sensorManager: SensorManager

        init {
            // handle prefs, other initialization
            renderer = MaterialRenderer()
            setRenderer(renderer)
            setRenderMode(RENDERMODE_CONTINUOUSLY)
        }

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
        }

        override fun onResume() {
            super.onResume()
            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_GAME)
        }

        override fun onPause() {
            super.onPause()
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

            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                renderer?.onSensorChanged(event)
            }
        }
    }


}

