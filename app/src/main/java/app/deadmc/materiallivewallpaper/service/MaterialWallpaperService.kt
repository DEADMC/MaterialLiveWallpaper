package app.deadmc.materiallivewallpaper.service

import android.opengl.GLSurfaceView
import app.deadmc.materiallivewallpaper.renderer.MaterialRenderer

/**
 * Created by adanilov on 08.08.2017.
 */
class MaterialWallpaperService : ReadyWallpaperService() {
    override fun getNewRenderer(): GLSurfaceView.Renderer {
        return MaterialRenderer()
    }
}