package euphoria.psycho.common

import android.graphics.*

fun Bitmap.getRoundCroppedBitmap(): Bitmap {
    val output = Bitmap.createBitmap(
        width, height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(output)

    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, width, height)

    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawCircle(
        (width / 2).toFloat(), (height / 2).toFloat(),
        width / 2f, paint
    )
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)
    return output
}