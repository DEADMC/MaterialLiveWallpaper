package deadmc.app.materiallivewallpaper.service

import android.graphics.Canvas
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import deadmc.app.materiallivewallpaper.figure.Triangle

class MaterialWallpaperService : WallpaperService(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    var x = 0f
    var y = 0f
    var z = 0f

    var curX = 0f
    var curY = 0f
    var curZ = 0f


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
        curX = values [0]*10
        curY = values [1]*10
        curZ = values [2]*10

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
                handler.postDelayed(drawRunner, 20)
            }

        }

        fun drawFigures(canvas: Canvas) {
            recalculateValues()
            drawSecondFigure(canvas)
            drawFirstFigure(canvas)
            drawThirdFigure(canvas)
            drawFourthFigure(canvas)

        }

        fun recalculateValues() {
            Log.e("tag","y ="+y+" curY = "+curY)
            Log.e("tag","x ="+x+" curX = "+curX)
            if (x-curX>0.01) x -= Math.abs(x-curX)/5
            if (y-curY>0.01) y -= Math.abs(y-curY)/5
            if (x-curX<-0.01) x += Math.abs(x-curX)/5
            if (y-curY<-0.01) y += Math.abs(y-curY)/5

        }

        fun drawFirstFigure(canvas: Canvas) {
            val speed = 1f
            val botX: Float = (width * 0.3 + x * speed).toFloat()
            val botY: Float = (height + 200 + y * speed).toFloat()
            val topX: Float = (width * 1.3 + x * speed).toFloat()
            val topY: Float = (-200 + y * speed).toFloat()
            val midX: Float = (width + 200 + x * speed).toFloat()
            val midY: Float = (height + 200 + y * speed).toFloat()
            val triangle = Triangle()
            triangle.drawFigure(canvas, topX, topY, botX, botY, midX, midY, Color.BLUE)
        }

        fun drawSecondFigure(canvas: Canvas) {
            val speed = 1.2f
            val botX: Float = (width * 0.2 + x * speed).toFloat()
            val botY: Float = (height + 200 + y * speed).toFloat()
            val topX: Float = (width * 1.2 + x * speed).toFloat()
            val topY: Float = (-200 + y * speed).toFloat()
            val midX: Float = (width + 200 + x * speed).toFloat()
            val midY: Float = (height + 200 + y * speed).toFloat()
            val triangle = Triangle()
            triangle.drawFigure(canvas, topX, topY, botX, botY, midX, midY, Color.WHITE)
        }

        fun drawThirdFigure(canvas: Canvas) {
            val speed = 1.2f
            val botX: Float = (width * 0.2 + x * speed).toFloat()
            val botY: Float = (height + 200 + y * speed).toFloat()
            val topX: Float = (width * 1.4 + x * speed).toFloat()
            val topY: Float = (-200 + y * speed).toFloat()
            val midX: Float = (width + 200 + x * speed).toFloat()
            val midY: Float = (height + 200 + y * speed).toFloat()
            val triangle = Triangle()
            triangle.drawFigure(canvas, topX, topY, botX, botY, midX, midY, Color.WHITE)
        }

        fun drawFourthFigure(canvas: Canvas) {
            val speed = 1.2f
            val botX: Float = (-0.2 * width + x * speed).toFloat()
            val botY: Float = (height * 0.6 + y * speed).toFloat()
            val topX: Float = (-0.2 * width + x * speed).toFloat()
            val topY: Float = (height * 1.2 + y * speed).toFloat()
            val midX: Float = (width * 1.2 + x * speed).toFloat()
            val midY: Float = (height * 1.2 + y * speed).toFloat()
            val triangle = Triangle()
            triangle.drawFigure(canvas, topX, topY, botX, botY, midX, midY, Color.WHITE)
        }

    }

}

