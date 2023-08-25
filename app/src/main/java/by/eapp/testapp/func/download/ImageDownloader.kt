package by.eapp.testapp.func.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class ImageDownloader(
    private val context: Context
) : DownloadImage {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    public override fun downloadImage(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI.and(DownloadManager.Request.NETWORK_MOBILE)
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("Image")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "image.jpeg"
            )
            .setAllowedOverMetered(true)

        return downloadManager.enqueue(request)
    }
}