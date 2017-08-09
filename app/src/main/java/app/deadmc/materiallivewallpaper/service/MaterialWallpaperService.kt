package app.deadmc.materiallivewallpaper.service

import android.opengl.GLSurfaceView
import app.deadmc.materiallivewallpaper.renderer.MaterialRenderer
import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer

/**
 * Created by adanilov on 08.08.2017.
 */
class MaterialWallpaperService : ReadyWallpaperService() {
    override fun getNewRenderer(): ReadyRenderer {
        return MaterialRenderer()
    }
}