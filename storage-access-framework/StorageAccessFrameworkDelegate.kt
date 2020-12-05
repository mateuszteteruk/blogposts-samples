import android.content.Context
import android.content.Intent
import android.net.Uri
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Used to expose interaction with Storage Access Framework
 */
class StorageAccessFrameworkDelegate constructor(
    private val context: Context
) {

    fun createFilePickerIntent(): Intent =
        Intent(Intent.ACTION_OPEN_DOCUMENT)
            .apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/*"
            }

    fun createFileSaverIntent(fileName: String): Intent =
        Intent(Intent.ACTION_CREATE_DOCUMENT)
            .apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/*"
                putExtra(Intent.EXTRA_TITLE, fileName)
            }

    fun write(uri: Uri, items: List<String>): Single<Boolean> {
        val outputStream = context.contentResolver.openOutputStream(uri) ?: return Single.just(false)
        return Single.fromCallable { outputStream }
            .map { os ->
                os.use { stream ->
                    stream.bufferedWriter().use { writer ->
                        items.asSequence()
                            .forEach { writer.append(it).appendLine() }
                    }
                }
                true
            }
            .onErrorReturn { false }
            .subscribeOn(Schedulers.io())
    }

    fun read(uri: Uri): Single<List<String>> {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return Single.just(emptyList())
        return Single.fromCallable { inputStream }
            .map { input ->
                input.use { stream ->
                    stream.bufferedReader().use { reader ->
                        reader.lineSequence().toList()
                    }
                }
            }
            .onErrorReturn { emptyList() }
            .subscribeOn(Schedulers.io())
    }

}