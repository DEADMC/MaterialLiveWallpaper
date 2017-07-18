package deadmc.app.materiallivewallpaper.activity

import android.app.Activity
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import deadmc.app.materiallivewallpaper.R
import deadmc.app.materiallivewallpaper.service.MaterialWallpaperService


/**
 * Created by adanilov on 18.07.2017.
 */
class SetWallpaperActivity : Activity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val intent = Intent(
                WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(this, MaterialWallpaperService::class.java))
        startActivity(intent)
    }

    override fun onClick(view: View) {

    }
}