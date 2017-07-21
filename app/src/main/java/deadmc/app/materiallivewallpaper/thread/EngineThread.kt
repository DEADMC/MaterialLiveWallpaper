package deadmc.app.materiallivewallpaper.thread

/**
 * Created by adanilov on 20.07.2017.
 */
class EngineThread : Thread(){
    var curX = 0f
    var curY = 0f
    var curZ = 0f

    @Synchronized fun setSensorValues(x:Float, y:Float,z:Float) {
        curX = x*10
        curY = y*10
        curZ = z*10
    }

    override fun run() {
        super.run()
    }
}