package deadmc.app.materiallivewallpaper.service

import android.graphics.Canvas
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import deadmc.app.materiallivewallpaper.figure.Square

class MaterialWallpaperService : WallpaperService(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    var x = 0f
    var y = 0f
    var z = 0f


    override fun onCreateEngine(): WallpaperService.Engine {
        return MaterialEngine()
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
        sensorManager.unregisterListener(this);
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
        // Movement
        x = values [0]
        y = values [1]
        z = values [2]

        Log.v("event", x.toString() + " " + y + " " + z)
    }


    inner class MaterialEngine : WallpaperService.Engine() {
        private var visible = true
        var width: Int = 0
        var height: Int = 0
        private val handler = Handler()
        private val drawRunner = Runnable { draw() }

        init {
            handler.post(drawRunner)
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            this.visible = false
            handler.removeCallbacks(drawRunner)
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int,
                                      width: Int, height: Int) {
            this.width = width
            this.height = height
            super.onSurfaceChanged(holder, format, width, height)
        }

        fun draw() {
            Log.e("engine", "draw started")
            val holder = surfaceHolder
            var canvas: Canvas? = null
            try {
                canvas = holder.lockCanvas()
                if (canvas != null) {
                    canvas.drawARGB(255, 225, 225, 255)
                    drawFigures(canvas)
                }
            } finally {
                if (canvas != null)
                    holder.unlockCanvasAndPost(canvas)
            }
            handler.removeCallbacks(drawRunner)
            if (visible) {
                handler.postDelayed(drawRunner, 10)
            }
        }

        fun drawFigures(canvas:Canvas) {
            val square = Square()
            val baseX = width/2+x*20
            val baseY = height/2+y*20
            canvas.drawRect(baseX,baseY,baseX+200,baseY+200,square.paint)
        }

    }

}

