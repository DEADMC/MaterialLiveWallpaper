package app.deadmc.materiallivewallpaper.model

import app.deadmc.materiallivewallpaper.renderer.ReadyRenderer
import app.deadmc.materiallivewallpaper.util.cubeCoords

/**
 * Created by adanilov on 18.08.2017.
 */
class Cube(renderer: ReadyRenderer):Square(renderer,triangleCoords = cubeCoords)