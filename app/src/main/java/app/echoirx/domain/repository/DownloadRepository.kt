package app.echoirx.domain.repository

import app.echoirx.domain.model.Download
import app.echoirx.domain.model.DownloadStatus
import app.echoirx.domain.model.PlaybackRequest
import app.echoirx.domain.model.PlaybackResponse
import kotlinx.coroutines.flow.Flow

interface DownloadRepository {
    suspend fun processDownload(
        downloadId: String,
        trackId: Long,
        quality: String,
        ac4: Boolean,
        immersive: Boolean,
        onProgress: suspend (Int) -> Unit
    ): Result<String>

    suspend fun getDownloadInfo(request: PlaybackRequest): Pair<PlaybackResponse, Map<String, String>>
    suspend fun saveDownload(download: Download)
    suspend fun updateDownloadProgress(downloadId: String, progress: Int)
    suspend fun updateDownloadStatus(downloadId: String, status: DownloadStatus)
    suspend fun updateDownloadFilePath(downloadId: String, filePath: String)
    suspend fun deleteDownload(download: Download)
    suspend fun getDownloadById(downloadId: String): Download?
    suspend fun getDownloadsByTrackId(trackId: Long): List<Download>
    suspend fun getDownloadsByAlbumId(albumId: Long): List<Download>
    suspend fun createAlbumDirectory(albumTitle: String, explicit: Boolean): String
    fun getActiveDownloads(): Flow<List<Download>>
    fun getDownloadHistory(): Flow<List<Download>>
}