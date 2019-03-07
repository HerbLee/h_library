package com.commonlib.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

/**
 *create by echo
 */
object QrCold {

    fun createBarCode(contents: String?): Bitmap? {
        val writer = MultiFormatWriter()
        var result: BitMatrix? = null
        val dswidth = 1080
        val dsheight = 432
        val barcodeFormat = BarcodeFormat.CODE_128
        try {
            result = writer.encode(contents, barcodeFormat, dswidth, dsheight)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        val width = result?.width
        val height = result?.height
        val pixels = IntArray(width!!*height!!)
        val BLACK = 0xff000000.toInt()
        val WHITE = 0xFFFFFFFF.toInt()

        for (index in 0 until height!!){
            val offset = index * width!!
            for (x in 0 until width){
                if (result?.get(x,index)!!){
                    pixels[offset+x] = BLACK
                }else{
                    pixels[offset+x] = WHITE
                }
            }
        }
        val bitmap = Bitmap.createBitmap(width!!,height,Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels,0,width,0,0,width,height)
        return bitmap
    }
}