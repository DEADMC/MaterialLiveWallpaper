package deadmc.app.materiallivewallpaper.service


import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.service.wallpaper.WallpaperService
import android.util.Log
import app.deadmc.materiallivewallpaper.opengles_classes.GLWallpaperService

import deadmc.app.materiallivewallpaper.renderer.MaterialRenderer



class MaterialWallpaperService : GLWallpaperService(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    private val renderer: MaterialRenderer? = null

    val TAG = this.javaClass.simpleName

    var x = 0f
    var y = 0f
    var z = 0f

    override fun onCreateEngine(): WallpaperService.Engine {
        val renderer = MaterialRenderer(this)
        return WallpaperEngine(null, baseContext, renderer, false)
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("onCreate", " register sensor ")
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        x = values [0]*1
        y = values [1]*1
        z = values [2]*1
    }

    internal inner class WallpaperEngine : GLEngine() {
        var renderer: MyRenderer? = null

        init {
            // handle prefs, other initialization
            renderer = MyRenderer()
            setRenderer(renderer)
            setRenderMode(RENDERMODE_CONTINUOUSLY)
        }

        fun onDestroy() {
            super.onDestroy()
            if (renderer != null) {
                renderer!!.release()
            }
            renderer = null
        }
    }


}

